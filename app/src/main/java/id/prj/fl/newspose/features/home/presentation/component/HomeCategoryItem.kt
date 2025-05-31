package id.prj.fl.newspose.features.home.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.BlueSemiGray
import id.prj.fl.newspose.ui.theme.DarkBlue
import id.prj.fl.newspose.ui.theme.DarkPastelBlue
import id.prj.fl.newspose.ui.theme.NewsPoseTheme
import id.prj.fl.newspose.ui.theme.White


@Composable
fun HomeCategories(
    listCategories: List<String>,
    selectedCategories: List<String>,
    onClick: (Boolean,String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        HomeNewsGroupLabel(stringResource(R.string.label_categories))
        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            listCategories.forEach {
                HomeCategoryItem(
                    label = it,
                    isCurrentCategory = selectedCategories.contains(it),
                    onCLick = onClick
                )
            }
        }
    }
}

@Composable
fun HomeCategoryItem(
    label: String,
    isCurrentCategory: Boolean,
    onCLick: (Boolean,String) -> Unit,
) {
    val backgroundColor by animateColorAsState(if (isCurrentCategory) DarkPastelBlue else BlueSemiGray)
    val textColor by animateColorAsState(if (isCurrentCategory) White else DarkBlue)

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onCLick.invoke(isCurrentCategory,label)
        },
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = label,
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 6.dp),
            color = textColor
        )
    }
}

@Preview
@Composable
private fun HomeCategoryItemPreview() {
    NewsPoseTheme {
        FlowRow {
            val listCategories = listOf(
                "Politic",
                "Economy",
                "Social",
                "Law",
                "Firm",
                "Business"
            )
            val selectedCategories = listOf(
                "Politic", "Law",
                "Firm",
            )
            HomeCategories(
                listCategories = listCategories,
                selectedCategories = selectedCategories
            ) {_, _ -> }
        }
    }
}