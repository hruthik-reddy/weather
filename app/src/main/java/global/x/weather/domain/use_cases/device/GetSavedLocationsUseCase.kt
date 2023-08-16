package global.x.weather.domain.use_cases.device

import global.x.weather.domain.models.FavoriteLocationModel
import javax.inject.Inject

class GetSavedLocationsUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): List<FavoriteLocationModel?> {
        return deviceRepository.getSavedLocations().map {
            it?.toFavoriteLocationModel()
        }
    }

}