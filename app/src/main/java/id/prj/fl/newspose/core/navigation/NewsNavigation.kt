package id.prj.fl.newspose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.prj.fl.newspose.features.home.presentation.view.HomeView
import id.prj.fl.newspose.features.newsdetail.presentation.view.NewsDetailView
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val newsUri: String)

@Composable
fun NewsNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
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
    }
}