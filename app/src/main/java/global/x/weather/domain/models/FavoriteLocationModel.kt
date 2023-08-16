package global.x.weather.domain.models

import global.x.weather.data.source.device.model.SavedLocationModel
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
        return "$name,\n$country"
    }

    fun toSavedLocationModel(): SavedLocationModel {
        return SavedLocationModel(
            id = id,
            name = name,
            region = region,
            country = country,
            isDefault = isDefault
        )
    }
}