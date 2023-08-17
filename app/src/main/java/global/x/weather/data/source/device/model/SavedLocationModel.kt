package global.x.weather.data.source.device.model

import global.x.weather.domain.models.FavoriteLocationModel

data class SavedLocationModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val isDefault: Boolean = false
) {
    fun toFavoriteLocationModel(): FavoriteLocationModel {
        return FavoriteLocationModel(
            id = id,
            name = name,
            region = region,
            country = country,
            isDefault = isDefault,
        )
    }
}