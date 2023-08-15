package global.x.weather.presentation.screen.favorites.model

import global.x.weather.infrastructure.util.StringUtil
import global.x.weather.presentation.screen.home.model.WeatherData

data class FavoriteLocationModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val isDefault: Boolean = false,
    val weatherData: WeatherData? = null
) {
    fun getDisplayName(): String {
     return   "$name,\n$country"
    }
}