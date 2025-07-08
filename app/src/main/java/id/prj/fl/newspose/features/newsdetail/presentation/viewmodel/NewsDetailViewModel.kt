package id.prj.fl.newspose.features.newsdetail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.newsdetail.domain.model.NewsDetailModel
import id.prj.fl.newspose.features.newsdetail.domain.usecase.CheckSavedBookMarksUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.GetNewsDetailUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.RemoveBookMarksByUriUseCase
import id.prj.fl.newspose.features.newsdetail.domain.usecase.SaveBookMarksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailNewsState(
    val loading: Boolean = false,
    val data: NewsDetailModel = NewsDetailModel(),
    val isAddToBookMarks: Boolean = false,
    val error: ErrorStatus? = null
)

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    private val saveBookMarksUseCase: SaveBookMarksUseCase,
    private val removeBookMarksByUriUseCase: RemoveBookMarksByUriUseCase,
    private val checkSavedBookMarksUseCase: CheckSavedBookMarksUseCase,
) : ViewModel() {

    private val articleUri: String = checkNotNull(savedStateHandle["newsUri"])

    private val _viewState: MutableStateFlow<DetailNewsState> = MutableStateFlow(DetailNewsState())
    val viewState = _viewState.asStateFlow()

    private fun getDetailNews() = viewModelScope.launch {
        _viewState.update { it.copy(loading = true) }
        getNewsDetailUseCase.invoke(articleUri).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update {
                        it.copy(
                            loading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    _viewState.update {
                        it.copy(
                            loading = false,
                            data = resource.data,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun saveBookMarks() = viewModelScope.launch {
        saveBookMarksUseCase.invoke(_viewState.value.data.info.toBookMarksModel()).apply {
            checkSavedBookMarks()
        }
    }

    fun removeBookMarks() = viewModelScope.launch {
        removeBookMarksByUriUseCase.invoke(articleUri).apply {
            checkSavedBookMarks()
        }
    }

    private fun checkSavedBookMarks() = viewModelScope.launch {
        _viewState.update { it.copy(loading = true) }
        checkSavedBookMarksUseCase.invoke(articleUri).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update { it.copy(loading = false) }
                    _viewState.update {
                        it.copy(
                            loading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    _viewState.update { it.copy(loading = false) }
                    _viewState.update {
                        it.copy(
                            loading = false,
                            isAddToBookMarks = resource.data
                        )
                    }
                }
            }
        }
    }

    init {
        getDetailNews()
        checkSavedBookMarks()
    }


}