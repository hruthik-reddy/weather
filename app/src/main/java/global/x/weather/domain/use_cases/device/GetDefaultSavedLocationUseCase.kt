package global.x.weather.domain.use_cases.device

import global.x.weather.domain.models.FavoriteLocationModel
import javax.inject.Inject

class GetDefaultSavedLocationUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): FavoriteLocationModel? {
        return deviceRepository.getDefaultSavedLocation()?.toFavoriteLocationModel()
    }
}