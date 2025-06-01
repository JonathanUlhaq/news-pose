package id.prj.fl.newspose.features.newsdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun NewsDetailHeader(
    title: String,
    date: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = title
        )
        Spacer(Modifier.height(14.dp))
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = date
        )

    }
}

@Composable
fun NewsDetailHeaderShimmer() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Box(
            Modifier
                .shimmer()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray)
                .fillMaxWidth()
                .height(80.dp)
        )
        Spacer(Modifier.height(14.dp))
        Box(
            Modifier
                .shimmer()
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray)
                .fillMaxWidth()
                .height(20.dp)
        )

    }
}

@Preview
@Composable
fun NewsDetailHeaderPreview() {
    NewsPoseTheme {
        Surface(color = Color.White) {
            Column {
                NewsDetailHeader(
                    title = "The Future of Sustainable Energy: Innovations and Challenges",
                    date = "2025-02-2023"
                )
                NewsDetailHeaderShimmer()
            }
        }
    }
}