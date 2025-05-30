package id.prj.fl.newspose.features.home.presentation.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.prj.fl.newspose.R
import id.prj.fl.newspose.core.dialog.NewsDialog
import id.prj.fl.newspose.core.dialog.rememberNewsDialogState
import id.prj.fl.newspose.features.home.presentation.component.HomeHotNewsItem
import id.prj.fl.newspose.features.home.presentation.component.HomeHotNewsShimmering
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsGroupLabel
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsItem
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsItemShimmering
import id.prj.fl.newspose.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val newsDialogState = rememberNewsDialogState()
    val lazyListState = rememberLazyListState()

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
        if (nextCurrentPage) viewModel.callNextNewsArticle()
    }

    viewState.value.error?.let { errorStatus ->
        newsDialogState.addError(errorStatus)
    }

    NewsDialog(
        state = newsDialogState,
        isLoading = viewState.value.isLoading,
    )

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {

        val articles = viewState.value.articles
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
        ) {
            item {
                val hotArticles = viewState.value.hotArticles
                Spacer(Modifier.height(12.dp))
                HomeNewsGroupLabel(
                    stringResource(R.string.header_trending_news)
                )
                Spacer(Modifier.height(3.dp))
                LazyRow {
                    if (hotArticles.isNotEmpty()) {
                        items(
                            items = hotArticles,
                            key = { it.id }
                        ) {
                            Spacer(Modifier.width(16.dp))
                            HomeHotNewsItem(it.title, it.body, it.image) {
                            }
                            Spacer(Modifier.width(16.dp))
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

            item {
                Spacer(Modifier.height(12.dp))
                HomeNewsGroupLabel(
                    stringResource(R.string.header_latest_news)
                )
            }

            if (articles.isNotEmpty()) {
                items(
                    items = articles,
                    key = { it.id }) { model ->
                    Spacer(Modifier.height(16.dp))
                    HomeNewsItem(model.title, model.body, model.image) {
                    }
                    Spacer(Modifier.height(16.dp))
                }
            } else {
                items(20) {
                    Spacer(Modifier.height(16.dp))
                    HomeNewsItemShimmering()
                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                if (viewState.value.isLoading && articles.isNotEmpty()) {
                    Spacer(Modifier.height(16.dp))
                    HomeNewsItemShimmering()
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

