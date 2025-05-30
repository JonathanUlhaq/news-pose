package id.prj.fl.newspose.core.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun NewsDialog(
    isVisible: Boolean,
    title: String,
    desc: String,
    onDismissRequest: () -> Unit
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(12.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewsDialogPreview() {
    NewsPoseTheme {
        NewsDialog(isVisible = true, title = "Title", desc = "Desc") {

        }
    }
}