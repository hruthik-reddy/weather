package global.x.weather.presentation.screen

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class WeatherNavigationRoute {
    object Home : WeatherNavigationRoute() {
       const val argumentName = "location"
        val args = listOf(navArgument(argumentName) { type = NavType.StringType })
        fun getName(): String {
            return "home"
        }
    }

    object Search : WeatherNavigationRoute() {
        fun getName(): String {
            return "search"
        }
    }

    object Favorite : WeatherNavigationRoute() {
        fun getName(): String {
            return "favorite"
        }
    }
}