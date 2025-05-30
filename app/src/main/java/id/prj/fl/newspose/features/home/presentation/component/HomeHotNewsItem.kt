package id.prj.fl.newspose.features.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun HomeHotNewsItem(
    title: String,
    desc: String,
    url: String,
    onClick: () -> Unit
) {
    val setTitle = if (title.length > 50) title.substring(0, 50) else title
    val setDesc = if (desc.length > 70) desc.substring(0, 70) else desc
    Box(
        modifier = Modifier
            .background(Color.White)
            .clickable { onClick.invoke() }
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            AsyncImage(
                model = url,
                placeholder = painterResource(R.drawable.bg_placeholder),
                error = painterResource(R.drawable.bg_placeholder),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .height(135.dp)
                    .width(240.dp)
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = setTitle,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .width(240.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = setDesc,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(240.dp)
            )
        }
    }
}

@Composable
fun HomeHotNewsShimmering() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .height(135.dp)
                    .width(240.dp)
            )
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .height(20.dp)
                    .width(240.dp)
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .shimmer()
                    .background(Color.Gray)
                    .height(40.dp)
                    .width(240.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeHotNewsPreview() {
    NewsPoseTheme {
      Column {
          HomeHotNewsItem(
              "Jack The Boy get Chocked fight in the podium",
              "Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo Little Jack got little money in little zoo",
              ""
          ) {

          }
          HomeHotNewsShimmering()
      }
    }
}
