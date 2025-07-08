package id.prj.fl.newspose.features.bookmarks.presentation.component

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.R.drawable.ic_unsaved_favorite
import id.prj.fl.newspose.ui.theme.DarkBlue
import id.prj.fl.newspose.ui.theme.NewsPoseTheme

@Composable
fun NewsDetailTopBar(
    isSaved: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val saveIcon by animateIntAsState(if (isSaved) R.drawable.ic_saved_favorite else ic_unsaved_favorite)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back",
                tint = DarkBlue,
                modifier = Modifier
                    .size(18.dp)
            )
        }

        Text(
            text = stringResource(R.string.jowo_pos),
            style = MaterialTheme.typography.headlineLarge
        )

        IconButton(
            onClick = onSaveClick
        ) {
            Icon(
                painter = painterResource(id = saveIcon),
                contentDescription = "Save",
                tint = DarkBlue,
                modifier = Modifier
                    .size(18.dp)
            )
        }
    }
}


@Preview
@Composable
fun NewsDetailTopBarPreview() {
    NewsPoseTheme {
        Surface(color = Color.White) {
            NewsDetailTopBar(
                isSaved = false,
                onBackClick = {},
                onSaveClick = {}
            )

        }
    }
}