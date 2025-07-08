package id.prj.fl.newspose.features.bookmarks.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.usecase.GetBookMarkByUriUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.CheckSavedBookMarksUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.RemoveBookMarksByUriUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.SaveBookMarksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailBookMarkState(
    val isLoading: Boolean = false,
    val bookMark: BookMarksModel = BookMarksModel(),
    val isAddToBookMarks: Boolean = false,
    val error: ErrorStatus? = null
)

@HiltViewModel
class NewsDetailBookMarkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBookMarkByUriUseCase: GetBookMarkByUriUseCase,
    private val saveBookMarksUseCase: SaveBookMarksUseCase,
    private val removeBookMarksByUriUseCase: RemoveBookMarksByUriUseCase,
    private val checkSavedBookMarksUseCase: CheckSavedBookMarksUseCase,
) : ViewModel() {

    private val articleUri: String = checkNotNull(savedStateHandle["newsUri"])
    private val _viewState: MutableStateFlow<DetailBookMarkState> = MutableStateFlow(
        DetailBookMarkState()
    )
    val viewState = _viewState.asStateFlow()

    fun getBookMarkByUri() = viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true) }
        getBookMarkByUriUseCase.invoke(articleUri).collect { resource ->
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
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            bookMark = resource.data,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun saveBookMarks() = viewModelScope.launch {
        saveBookMarksUseCase.invoke(_viewState.value.bookMark).apply {
            checkSavedBookMarks()
        }
    }

    fun removeBookMarks() = viewModelScope.launch {
        removeBookMarksByUriUseCase.invoke(articleUri).apply {
            checkSavedBookMarks()
        }
    }

    private fun checkSavedBookMarks() = viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true) }
        checkSavedBookMarksUseCase.invoke(articleUri).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update { it.copy(isLoading = false) }
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    _viewState.update { it.copy(isLoading = false) }
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            isAddToBookMarks = resource.data
                        )
                    }
                }
            }
        }
    }

    init {
        getBookMarkByUri()
        checkSavedBookMarks()
    }
}