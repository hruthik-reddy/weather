package global.x.weather.domain.use_cases.device

import global.x.weather.data.source.device.DeviceDataSource
import global.x.weather.domain.models.FavoriteLocationModel
import javax.inject.Inject

class DeleteSavedLocationUseCase @Inject constructor(private val deviceDataSource: DeviceDataSource) {
    operator fun invoke(locations: List<FavoriteLocationModel>) {
        deviceDataSource.deleteSavedLocations(locations = locations.map { it.toSavedLocationModel()})
    }
}