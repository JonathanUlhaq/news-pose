package id.prj.fl.newspose.core.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.core.model.BottomNavigationItemModel
import id.prj.fl.newspose.core.model.NavigationItem
import id.prj.fl.newspose.ui.theme.DarkBlue
import id.prj.fl.newspose.ui.theme.NewsPoseTheme
import id.prj.fl.newspose.ui.theme.SteelBlue


@Composable
fun NewsBottomBar(
    modifier: Modifier = Modifier,
    onClick: (NavigationItem) -> Unit,
) {
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomNavigationItems = listOf(
        BottomNavigationItemModel(
            title = NavigationItem.HOME,
            textColor = SteelBlue,
            selectedTextColor = DarkBlue,
            icon = R.drawable.ic_unselect_home,
            selectedIcon = R.drawable.ic_selected_home,
        ),
        BottomNavigationItemModel(
            title = NavigationItem.EXPLORE,
            textColor = SteelBlue,
            selectedTextColor = DarkBlue,
            icon = R.drawable.ic_unselect_explore,
            selectedIcon = R.drawable.ic_selected_explore,
        ),
        BottomNavigationItemModel(
            title = NavigationItem.BOOKMARKS,
            textColor = SteelBlue,
            selectedTextColor = DarkBlue,
            icon = R.drawable.ic_unselect_bookmarks,
            selectedIcon = R.drawable.ic_selected_bookmarks,
        )
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RectangleShape,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            bottomNavigationItems.forEachIndexed { index, item ->
                NewsBottomBarItem(
                    navigationItem = item.title,
                    textColor = item.textColor,
                    selectedTextColor = item.selectedTextColor,
                    icon = item.icon,
                    selectedIcon = item.selectedIcon,
                    isSelected = index == selectedItem,
                ) {
                    selectedItem = index
                    onClick.invoke(item.title)
                }
            }
        }
    }
}

@Composable
private fun NewsBottomBarItem(
    navigationItem: NavigationItem,
    textColor: Color,
    selectedTextColor: Color,
    icon: Int,
    selectedIcon: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val navItemIcon by animateIntAsState(if (isSelected) selectedIcon else icon)
    val navItemTextColor by animateColorAsState(if (isSelected) selectedTextColor else textColor)

    val title = when (navigationItem) {
        NavigationItem.HOME -> "Home"
        NavigationItem.EXPLORE -> "Explore"
        NavigationItem.BOOKMARKS -> "Bookmarks"
    }

    Surface(
        color = Color.Transparent,
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(navItemIcon),
                contentDescription = title,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = navItemTextColor,
            )
        }
    }
}

@Preview
@Composable
private fun NewsBottomBarPreview() {
    NewsPoseTheme {
        NewsBottomBar {

        }
    }
}