package id.prj.fl.newspose.features.explore.presentation.view

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import id.prj.fl.newspose.core.dialog.NewsDialog
import id.prj.fl.newspose.core.dialog.rememberNewsDialogState
import id.prj.fl.newspose.features.explore.presentation.viewmodel.ExploreNewsViewModel
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.features.explore.presentation.component.ExploreSearchField
import id.prj.fl.newspose.features.explore.presentation.component.ExploreRelevantNews
import id.prj.fl.newspose.features.explore.presentation.component.ExploreRelevantNewsShimmering
import id.prj.fl.newspose.features.explore.presentation.component.ExploreSearchHistoryItem

@Composable
fun ExploreNewsView(
    viewModel: ExploreNewsViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit,
) {
    val viewState = viewModel.viewState.collectAsState()
    val newsDialog = rememberNewsDialogState()
    val labelWording by animateIntAsState(if (viewState.value.newsModel.isNotEmpty()) R.string.relevant_result else R.string.search_history)
    val lazyListState = rememberLazyListState()

    viewState.value.errorStatus?.let { errorStatus ->
        newsDialog.addError(errorStatus)
    }

    val isNextPage by remember {
        derivedStateOf {
            return@derivedStateOf if (viewState.value.newsModel.isNotEmpty()) {
                val currentNewsCount = viewState.value.newsModel.size
                val lastDisplayedNews =
                    lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastDisplayedNews >= currentNewsCount - 1
            } else {
                false
            }
        }
    }

    LaunchedEffect(key1 = isNextPage) {
        if (isNextPage) {
            viewModel.callSearchNewsArticles(false)
        }
    }

    NewsDialog(newsDialog)

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Column {
            ExploreSearchField(
                value = viewModel.getKeywordValue(),
                onValueChange = viewModel::setKeywordValue,
                onFocus = {
                    //not used
                },
                onDone = {
                    viewModel.addSearchHistory()
                    viewModel.callSearchNewsArticles(false)
                },
                onDelete = {
                    viewModel.setKeywordValue("")
                }
            )
            Spacer(Modifier.height(16.dp))
            if (viewState.value.searchHistory.isNotEmpty() || viewState.value.newsModel.isNotEmpty() && !viewState.value.loading) {
                Text(
                    style = MaterialTheme.typography.headlineLarge,
                    text = stringResource(labelWording)
                )
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                state = lazyListState,
            ) {
                if (viewState.value.newsModel.isEmpty() && viewState.value.searchHistory.isNotEmpty() && !viewState.value.loading) {
                    items(viewState.value.searchHistory) { searchHistory ->
                        ExploreSearchHistoryItem(searchHistory) {
                            viewModel.callSearchNewsArticles(true, searchHistory)
                        }
                        Spacer(Modifier.height(14.dp))
                    }
                }

                if (!viewState.value.loading) {
                    if (viewState.value.newsModel.isNotEmpty()) {
                        items(
                            items = viewState.value.newsModel,
                            key = { it.uri }) { model ->
                            ExploreRelevantNews(
                                url = model.image,
                                title = model.title,
                                date = model.date,
                                type = model.dataType
                            ) {
                                onItemClick.invoke(model.uri)
                            }
                            Spacer(Modifier.height(14.dp))
                        }
                    }
                } else {
                    items(10) {
                        ExploreRelevantNewsShimmering()
                        Spacer(Modifier.height(14.dp))
                    }
                }

                if (viewState.value.loadingPagination) {
                    item {
                        Spacer(Modifier.height(14.dp))
                        ExploreRelevantNewsShimmering()
                        Spacer(Modifier.height(14.dp))
                    }
                }
            }
        }
    }
}