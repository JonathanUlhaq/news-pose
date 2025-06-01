package id.prj.fl.newspose.features.newsdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.ui.theme.DarkPastelBlue

@Composable
fun NewsDetailBody(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .padding(horizontal = 12.dp)
    )
}

@Composable
fun NewsDetailBodyShimmer() {
    Box(
        Modifier
            .shimmer()
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(DarkPastelBlue)
            .height(300.dp)
    )
}