package id.prj.fl.newspose.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.prj.fl.newspose.core.component.MainTopBar
import id.prj.fl.newspose.core.model.NavigationItem
import id.prj.fl.newspose.features.bookmarks.presentation.view.ListBookMarksView
import id.prj.fl.newspose.features.bookmarks.presentation.view.NewsDetailBookMarkView
import id.prj.fl.newspose.features.explore.presentation.view.ExploreNewsView
import id.prj.fl.newspose.features.home.presentation.view.HomeView
import id.prj.fl.newspose.features.newsdetail.presentation.view.NewsDetailView
import id.prj.fl.newspose.ui.theme.White
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Explore

@Serializable
object Bookmark

@Serializable
data class Detail(val newsUri: String)

@Serializable
data class DetailBookMark(val newsUri: String)

@Composable
fun NewsNavigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomBarRoute = listOf(
        Home::class.qualifiedName,
        Explore::class.qualifiedName,
        Bookmark::class.qualifiedName
    )

    val localDensity = LocalDensity.current

    var bottomPadding by remember {
        mutableStateOf(0.dp)
    }

    var topPadding by remember {
        mutableStateOf(0.dp)
    }

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }


    Surface(
        color = White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier =
                Modifier.fillMaxSize()
        ) {
            if (currentRoute in bottomBarRoute) {
                MainTopBar(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            topPadding =
                                with(localDensity) { coordinates.size.height.toDp() }
                        }
                )
            } else {
                topPadding = 0.dp
            }

            NavHost(
                navController = navController,
                startDestination = Home,
                modifier = Modifier
                    .padding(bottom = bottomPadding, top = topPadding)
            ) {
                composable<Home> {
                    HomeView { newsUri ->
                        navController.navigate(Detail(newsUri))
                    }
                }

                composable<Detail> {
                    NewsDetailView {
                        navController.navigateUp()
                    }
                }

                composable<Explore> {
                    ExploreNewsView { newsUri ->
                        navController.navigate(Detail(newsUri))
                    }
                }

                composable<Bookmark> {
                    ListBookMarksView { uri ->
                        navController.navigate(DetailBookMark(uri))
                    }
                }

                composable<DetailBookMark> {
                    NewsDetailBookMarkView {
                        navController.navigateUp()
                    }
                }
            }

            if (currentRoute in bottomBarRoute) {
                NewsBottomBar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .onGloballyPositioned { coordinates ->
                            bottomPadding =
                                with(localDensity) { coordinates.size.height.toDp() }
                        },
                    selectedItem = selectedItem,
                    setSelectedItem = { index ->
                        selectedItem = index
                    }
                ) { navItem ->
                    when (navItem) {
                        NavigationItem.HOME -> {
                            navController.navigate(Home) {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        NavigationItem.EXPLORE -> {
                            navController.navigate(Explore) {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        NavigationItem.BOOKMARKS -> {
                            navController.navigate(Bookmark) {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            } else {
                bottomPadding = 0.dp
            }
        }
    }
}