package id.prj.fl.newspose.features.home.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun HomeNewsItem(
    title: String,
    desc: String,
    url: String,
    onClick: () -> Unit,
) {
    val setDesc = if (desc.length > 100) desc.substring(0, 100) else desc
    val setTitle = if (title.length > 50) title.substring(0, 50) else title
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clickable { onClick.invoke() }
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = setTitle,
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(Modifier.height(6.dp))
                Text(
                    text = setDesc,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
                    .height(120.dp)
                    .width(138.dp)
            )

        }
    }
}

@Composable
fun HomeNewsItemShimmering() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .width(150.dp)
                    .height(100.dp)
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .width(138.dp)
                    .height(138.dp)
            )
        }
    }
}


@Preview
@Composable
fun HomeNewsItemPreview() {
    NewsPoseTheme {
      Column {
          HomeNewsItem(
              "Jack",
              "Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo",
              ""
          ) {

          }
          Spacer(Modifier.height(20.dp))
          HomeNewsItemShimmering()
      }
    }
}