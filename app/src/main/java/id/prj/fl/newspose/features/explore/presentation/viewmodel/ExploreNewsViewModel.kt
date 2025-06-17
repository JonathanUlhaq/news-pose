package id.prj.fl.newspose.features.explore.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.prj.fl.newspose.core.datastore.NewsDataStore
import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.explore.domain.model.ExploreNewsModel
import id.prj.fl.newspose.features.explore.domain.usecase.SearchNewsArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExploreNewsState(
    val searchHistory: List<String> = emptyList(),
    val newsModel: List<ExploreNewsModel.ArticleResultModel.ArticlesListResultModel> = emptyList(),
    val loading: Boolean = false,
    val loadingPagination: Boolean = false,
    val errorStatus: ErrorStatus? = null
)

@HiltViewModel
class ExploreNewsViewModel @Inject constructor(
    private val newsStore: NewsDataStore,
    private val searchNewsArticlesUseCase: SearchNewsArticlesUseCase
) : ViewModel() {
    private val _viewState: MutableStateFlow<ExploreNewsState> =
        MutableStateFlow(ExploreNewsState())
    val viewState = _viewState.asStateFlow()
    private val listSearchHistory = mutableStateListOf("")
    private var keyword by mutableStateOf("")
    private var page by mutableIntStateOf(1)

    init {
        viewModelScope.launch {
            newsStore.getHistoryList().collect { list ->
                _viewState.update { state ->
                    state.copy(searchHistory = list)
                }
                listSearchHistory.addAll(list)
            }
        }
    }

    fun callSearchNewsArticles(isNewsSearch: Boolean, inputKeyword: String = "") =
        viewModelScope.launch {
            if (inputKeyword.isNotEmpty()) {
                keyword = inputKeyword
            }
            if (isNewsSearch) {
                page = 1
                _viewState.update { it.copy(loading = true, loadingPagination = false) }
            } else {
                _viewState.update { it.copy(loading = false, loadingPagination = true) }
            }
            if (page > 5) return@launch
            searchNewsArticlesUseCase.invoke(
                sortBy = "rel",
                keyword = inputKeyword.ifEmpty { keyword },
                articleCounts = 15,
                page = page
            ).collect { resource ->
                when (resource) {
                    is ResourceHandler.Error -> {
                        _viewState.update {
                            it.copy(
                                loading = false,
                                loadingPagination = false,
                                errorStatus = resource.errorStatus,
                                newsModel = emptyList()
                            )
                        }
                    }

                    is ResourceHandler.Success -> {
                        page++
                        _viewState.update {
                            if (isNewsSearch) {
                                it.copy(
                                    loading = false,
                                    loadingPagination = false,
                                    errorStatus = null,
                                    newsModel = resource.data.articles.results
                                )
                            } else {
                                it.copy(
                                    loading = false,
                                    loadingPagination = false,
                                    errorStatus = null,
                                    newsModel = it.newsModel + resource.data.articles.results
                                )
                            }
                        }
                    }
                }
            }
        }

    fun addSearchHistory() = viewModelScope.launch {
        if (keyword in listSearchHistory) return@launch
        if (listSearchHistory.size > 4) {
            listSearchHistory.removeRange(0,2)
            listSearchHistory.add(keyword)
            newsStore.storeHistoryList(listSearchHistory)
        } else {
            listSearchHistory.add(keyword)
            newsStore.storeHistoryList(listSearchHistory)

        }
    }

    fun getKeywordValue(): String = keyword
    fun setKeywordValue(value: String) {
        keyword = value
    }


}