package id.prj.fl.newspose.features.bookmarks.presentation.view

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import id.prj.fl.newspose.core.dialog.NewsDialog
import id.prj.fl.newspose.core.dialog.rememberNewsDialogState
import id.prj.fl.newspose.features.bookmarks.presentation.component.ListBookMarkItemShimmering
import id.prj.fl.newspose.features.bookmarks.presentation.component.ListBookMakItem
import id.prj.fl.newspose.features.bookmarks.presentation.viewmodel.ListBookMarksViewModel

@Composable
fun ListBookMarksView(
    viewModel: ListBookMarksViewModel = hiltViewModel(),
    onBookMarkClick: (String) -> Unit
) {
    val viewSate = viewModel.viewState.collectAsState()
    val newsDialogState = rememberNewsDialogState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewSate.value.error) {
        viewSate.value.error?.let {
            newsDialogState.addError(it)
        }
    }

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver {_, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.getListBookMarks(true)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    NewsDialog(newsDialogState)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            if (viewSate.value.isLoading) {
                item {
                    ListBookMarkItemShimmering()
                }
            } else {
                items(viewSate.value.bookMarks) {
                    Spacer(Modifier.height(12.dp))
                    Log.d("Cek resource data", it.toString())
                    ListBookMakItem(
                        url = it.image,
                        title = it.title,
                        date = it.date,
                        type = it.dataType,
                    ) {
                        onBookMarkClick.invoke(it.uri)
                    }
                }
            }
        }
    }
}