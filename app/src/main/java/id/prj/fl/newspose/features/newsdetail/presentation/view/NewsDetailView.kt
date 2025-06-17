package id.prj.fl.newspose.features.newsdetail.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.prj.fl.newspose.core.dialog.NewsDialog
import id.prj.fl.newspose.core.dialog.rememberNewsDialogState
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailBody
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailBodyShimmer
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailHeader
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailHeaderShimmer
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailImage
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailImageShimmer
import id.prj.fl.newspose.features.newsdetail.presentation.component.NewsDetailTopBar
import id.prj.fl.newspose.features.newsdetail.presentation.viewmodel.NewsDetailViewModel

@Composable
fun NewsDetailView(
    viewModel: NewsDetailViewModel = hiltViewModel(),
    onBackFinish: () -> Unit
) {
    val viewState = viewModel.viewState.collectAsState().value
    val scrollState = rememberScrollState()
    val newsDialog = rememberNewsDialogState()

    viewState.error?.let { errorStatus ->
        newsDialog.addError(errorStatus)
    }

    NewsDialog(
        state = newsDialog
    )


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NewsDetailTopBar(
                isSaved = false,
                onBackClick = onBackFinish,
                onSaveClick = {}
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                if (viewState.loading) {
                    Spacer(Modifier.height(20.dp))
                    NewsDetailHeaderShimmer()
                    Spacer(Modifier.height(12.dp))
                    NewsDetailImageShimmer()
                    Spacer(Modifier.height(12.dp))
                    NewsDetailBodyShimmer()
                } else {
                    Spacer(Modifier.height(20.dp))
                    NewsDetailHeader(
                        title = viewState.data.info.title,
                        date = viewState.data.info.date
                    )
                    Spacer(Modifier.height(12.dp))
                    NewsDetailImage(viewState.data.info.image)
                    Spacer(Modifier.height(20.dp))
                    NewsDetailBody(viewState.data.info.body)
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }

}