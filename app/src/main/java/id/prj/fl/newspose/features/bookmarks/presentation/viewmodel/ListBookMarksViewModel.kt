package id.prj.fl.newspose.features.bookmarks.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import dagger.hilt.android.lifecycle.HiltViewModel
import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.usecase.GetBookMarksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListBookMarksState(
    val isLoading: Boolean = false,
    val bookMarks: List<BookMarksModel> = emptyList(),
    val error: ErrorStatus? = null,
    val hasMoreData: Boolean = false
)

@HiltViewModel
@OptIn(SavedStateHandleSaveableApi::class)
class ListBookMarksViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListBookMarks: GetBookMarksUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<ListBookMarksState> =
        MutableStateFlow(ListBookMarksState())

    val viewState = _viewState.asStateFlow()

    private var currentPage by savedStateHandle.saveable {
        mutableIntStateOf(1)
    }

    fun getListBookMarks(isInitCall: Boolean) = viewModelScope.launch {
        if (isInitCall) {
            currentPage = 1
        }
        _viewState.value = _viewState.value.copy(isLoading = true)
        getListBookMarks.invoke(10, currentPage).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    currentPage += 1
                    _viewState.update {
                        Log.d("Cek resource data", resource.data.toString())
                        if (isInitCall) {
                            it.copy(
                                isLoading = false,
                                bookMarks = resource.data
                            )
                        } else {
                            it.copy(
                                isLoading = false,
                                bookMarks = it.bookMarks + resource.data,
                                hasMoreData = resource.data.size >= 10
                            )
                        }
                    }
                }
            }
        }
    }

    init {
        getListBookMarks(true)
    }

}