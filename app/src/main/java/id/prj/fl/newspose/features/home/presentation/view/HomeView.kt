package id.prj.fl.newspose.features.home.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.prj.fl.newspose.R
import id.prj.fl.newspose.core.dialog.NewsDialog
import id.prj.fl.newspose.core.dialog.rememberNewsDialogState
import id.prj.fl.newspose.features.home.presentation.component.HomeCategories
import id.prj.fl.newspose.features.home.presentation.component.HomeHotNewsItem
import id.prj.fl.newspose.features.home.presentation.component.HomeHotNewsShimmering
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsGroupLabel
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsItem
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsItemShimmering
import id.prj.fl.newspose.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewsClick:(String) -> Unit,
) {
    val viewState = viewModel.viewState.collectAsState()
    val newsDialogState = rememberNewsDialogState()
    val lazyListState = rememberLazyListState()
    var apiCalledByCategories by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = viewModel.selectedCategories().size) {
        if (!apiCalledByCategories) return@LaunchedEffect
        viewModel.callNewsArticle()
        viewModel.callHotNewsArticle()
        apiCalledByCategories = false
    }

    val isCategoriesVisible: Boolean by remember {
        derivedStateOf {
            return@derivedStateOf lazyListState.firstVisibleItemIndex <= 4
        }
    }

    val nextCurrentPage: Boolean by remember {
        derivedStateOf {
            return@derivedStateOf if (viewState.value.articles.isNotEmpty()) {
                val currentNewsCount = viewState.value.articles.size
                val lastDisplayedNews =
                    lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastDisplayedNews >= currentNewsCount - 1
            } else {
                false
            }
        }
    }

    LaunchedEffect(key1 = nextCurrentPage) {
        if (nextCurrentPage) viewModel.callNextNewsArticle(false)
    }

    viewState.value.error?.let { errorStatus ->
        newsDialogState.addError(errorStatus)
    }

    NewsDialog(
        state = newsDialogState,
    )

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {

        val articles = viewState.value.articles
        Column {
            AnimatedVisibility(isCategoriesVisible) {
                HomeCategories(
                    listCategories = viewModel.newsCategories,
                    selectedCategories = viewModel.selectedCategories()
                ) { isCurrentCategory, label ->
                    apiCalledByCategories = true
                    if (isCurrentCategory) {
                        viewModel.removeSelectedCategories(label)
                    } else {
                        viewModel.addSelectedCategories(label)
                    }
                }
            }

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
            ) {
                item {
                    val hotArticles = viewState.value.hotArticles
                    if (hotArticles.isNotEmpty() && !viewState.value.isLoading) {
                        Spacer(Modifier.height(12.dp))
                        HomeNewsGroupLabel(
                            stringResource(R.string.header_trending_news)
                        )
                        Spacer(Modifier.height(3.dp))

                    }
                    LazyRow {
                        if (!viewState.value.isLoading) {
                            if (hotArticles.isNotEmpty()) {
                                items(
                                    items = hotArticles,
                                    key = { it.uri }
                                ) {
                                    Spacer(Modifier.width(16.dp))
                                    HomeHotNewsItem(it.title, it.body, it.image) {
                                        onNewsClick.invoke(it.uri)
                                    }
                                    Spacer(Modifier.width(16.dp))
                                }
                            }
                        } else {
                            items(10) {
                                Spacer(Modifier.width(16.dp))
                                HomeHotNewsShimmering()
                                Spacer(Modifier.width(16.dp))
                            }
                        }
                    }
                }

                if (articles.isNotEmpty() && !viewState.value.isLoading) {
                    item {
                        Spacer(Modifier.height(12.dp))
                        HomeNewsGroupLabel(
                            stringResource(R.string.header_latest_news)
                        )
                    }
                }

                if (!viewState.value.isLoading) {
                    if (articles.isNotEmpty()) {
                        items(
                            items = articles,
                            key = { it.uri }) { model ->
                            Spacer(Modifier.height(16.dp))
                            HomeNewsItem(model.title, model.body, model.image) {
                                onNewsClick.invoke(model.uri)
                            }
                            Spacer(Modifier.height(16.dp))
                        }
                    } else {
                        item {
                            Spacer(Modifier.width(16.dp))
                            Text(
                                style = MaterialTheme.typography.labelLarge,
                                text = stringResource(R.string.label_list_news_empty),
                                modifier = Modifier
                                    .padding(12.dp)
                            )
                            Spacer(Modifier.width(16.dp))
                        }
                    }
                } else {
                    items(20) {
                        Spacer(Modifier.height(16.dp))
                        HomeNewsItemShimmering()
                        Spacer(Modifier.height(16.dp))
                    }
                }

                item {
                    if (viewState.value.isPagingLoading && articles.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        HomeNewsItemShimmering()
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

