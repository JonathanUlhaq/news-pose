package id.prj.fl.newspose.core.model

import androidx.compose.ui.graphics.Color

enum class NavigationItem {
    HOME,
    EXPLORE,
    BOOKMARKS
}

data class BottomNavigationItemModel(
    val title:NavigationItem,
    val textColor:Color,
    val selectedTextColor:Color,
    val icon:Int,
    val selectedIcon:Int,
)
