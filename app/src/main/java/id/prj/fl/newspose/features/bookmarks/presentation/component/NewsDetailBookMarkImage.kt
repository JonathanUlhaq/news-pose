package id.prj.fl.newspose.features.bookmarks.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.DarkPastelBlue

@Composable
fun NewsDetailImage(
    url: String
) {
    AsyncImage(
        model = url,
        contentDescription = "News Image",
        placeholder = painterResource(R.drawable.bg_placeholder),
        error = painterResource(R.drawable.bg_placeholder),
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun NewsDetailImageShimmer() {
    Box(
        Modifier
            .shimmer()
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(DarkPastelBlue)
            .height(200.dp)
    )
}
