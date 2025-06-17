package id.prj.fl.newspose.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.features.home.presentation.component.HomeNewsGroupLabel
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(12.dp)
    ) {
        HomeNewsGroupLabel(stringResource(R.string.jowo_pos))
    }
}

@Preview
@Composable
private fun MainTopBarPreview() {
    NewsPoseTheme {
        Surface(color = Color.White) {
            MainTopBar()
        }
    }
}