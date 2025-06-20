package id.prj.fl.newspose.features.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.AliceBlue
import id.prj.fl.newspose.ui.theme.DarkBlue
import id.prj.fl.newspose.ui.theme.NewsPoseTheme
import id.prj.fl.newspose.ui.theme.White

@Composable
fun ExploreSearchHistoryItem(
    label: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick.invoke() }
    ) {
        Surface(
            color = AliceBlue,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_history),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(14.dp)
                )
            }
        }
        Spacer(Modifier.width(12.dp))
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = label,
            color = DarkBlue
        )
    }
}

@Preview
@Composable
private fun ExploreSearchHistoryItemPreview() {
    NewsPoseTheme {
        Surface(color = White, modifier = Modifier.padding(12.dp)) {
            ExploreSearchHistoryItem("Test") {

            }
        }
    }
}