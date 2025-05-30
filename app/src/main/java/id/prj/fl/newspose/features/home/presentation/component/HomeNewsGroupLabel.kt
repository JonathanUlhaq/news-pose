package id.prj.fl.newspose.features.home.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeNewsGroupLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .padding(12.dp)
    )
}