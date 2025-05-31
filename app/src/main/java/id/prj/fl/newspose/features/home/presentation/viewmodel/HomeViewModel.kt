package id.prj.fl.newspose.features.home.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import dagger.hilt.android.lifecycle.HiltViewModel
import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.home.domain.model.ArticlesModel
import id.prj.fl.newspose.features.home.domain.usecase.GetNewsArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val articles: List<ArticlesModel.ArticleResultModel.ArticlesListResultModel> = emptyList(),
    val hotArticles: List<ArticlesModel.ArticleResultModel.ArticlesListResultModel> = emptyList(),
    val isLoading: Boolean = false,
    val isPagingLoading: Boolean = false,
    val error: ErrorStatus? = null
)

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeUiState())
    val viewState = _viewState.asStateFlow()

    private var currentPage by savedStateHandle.saveable {
        mutableIntStateOf(-1)
    }

    private val selectedCategories: MutableList<String> by savedStateHandle.saveable(
        saver = listSaver(
            save = { value ->
                value.toList()
            },
            restore = { list ->
                list.toMutableStateList()
            }
        )
    ) {
        mutableStateListOf()
    }

    val newsCategories = listOf(
        "Indonesia",
        "Politic",
        "Economy",
        "Social",
        "Law",
        "Firm",
    )

    init {
        callNewsArticle()
        callHotNewsArticle()
    }


    fun callNewsArticle() = viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true, isPagingLoading = false) }
        getNewsArticlesUseCase.invoke(
            sortBy = "date",
            keyword = selectedCategories.ifEmpty { listOf("all") },
            articleCounts = 10,
            page = 1,
        ).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            isPagingLoading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    currentPage = 1
                    currentPage += resource.data.page
                    _viewState.update {
                        it.copy(
                            error = null,
                            isLoading = false,
                            isPagingLoading = false,
                            articles = resource.data.articles.results
                        )
                    }
                }
            }
        }
    }

    fun callHotNewsArticle() = viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true, isPagingLoading = false) }

        getNewsArticlesUseCase.invoke(
            sortBy = "sourceAlexaGlobalRank",
            keyword = selectedCategories.ifEmpty { listOf("all") },
            articleCounts = 5,
            page = 1,
        ).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update { it.copy(isLoading = false, error = resource.errorStatus) }
                }

                is ResourceHandler.Success -> {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            isPagingLoading = false,
                            hotArticles = resource.data.articles.results
                        )
                    }
                }
            }
        }
    }

    fun callNextNewsArticle(isNewCategories:Boolean) = viewModelScope.launch {
        val nextPage = if (isNewCategories) 1 else currentPage + 1
        if (nextPage > 5) return@launch
        _viewState.update { it.copy(isLoading = false, isPagingLoading = true) }
        getNewsArticlesUseCase.invoke(
            sortBy = null,
            keyword = selectedCategories.ifEmpty { listOf("all") },
            articleCounts = 15,
            page = nextPage,
        ).collect { resource ->
            when (resource) {
                is ResourceHandler.Error -> {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            isPagingLoading = false,
                            error = resource.errorStatus
                        )
                    }
                }

                is ResourceHandler.Success -> {
                    _viewState.update {
                        it.copy(
                            error = null,
                            isLoading = false,
                            isPagingLoading = false,
                            articles = if (isNewCategories) resource.data.articles.results else it.articles + resource.data.articles.results
                        )
                    }
                    currentPage = nextPage
                }
            }
        }

    }

    fun selectedCategories(): MutableList<String> = selectedCategories

    fun addSelectedCategories(categories: String) {
        selectedCategories.add(categories)
    }

    fun removeSelectedCategories(categories: String) {
        selectedCategories.remove(categories)
    }
}