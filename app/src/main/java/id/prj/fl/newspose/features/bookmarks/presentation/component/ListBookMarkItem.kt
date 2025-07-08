package id.prj.fl.newspose.features.bookmarks.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.NewsPoseTheme
import id.prj.fl.newspose.ui.theme.White

@Composable
fun ListBookMakItem(
    url: String,
    title: String,
    date: String,
    type: String,
    onClick: () -> Unit,
) {
    val setTitle = if (title.length > 30) title.substring(0, 30) else title

    Surface(
        onClick = onClick,
        color = Color.Transparent
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .listener(onError = { _, result ->
                        Log.e("COIL_ERROR", "Failed to load image: ${result.throwable}")
                        result.throwable.printStackTrace()
                    })
                    .build(),
                placeholder = painterResource(R.drawable.bg_placeholder),
                error = painterResource(R.drawable.bg_placeholder),
                contentDescription = setTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .height(80.dp)
                    .width(120.dp)
            )
            Spacer(Modifier.width(14.dp))
            Column {
                Text(
                    text = setTitle,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(14.dp))
                Text(
                    text = "$type • $date",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun ListBookMarkItemShimmering() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .shimmer()
                .background(Color.Gray)
                .height(80.dp)
                .width(120.dp)
        )
        Spacer(Modifier.width(14.dp))
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .height(30.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .height(20.dp)
                    .fillMaxWidth(0.6f)
            )
        }
    }
}

@Preview
@Composable
private fun ExploreSearchTopResultPreview() {
    NewsPoseTheme {
        Surface(
            color = White,
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                ListBookMakItem(
                    "www.www.www",
                    "Test 123",
                    "2025-1-20",
                    "News"
                ) { }
                ListBookMarkItemShimmering()
            }
        }
    }
}