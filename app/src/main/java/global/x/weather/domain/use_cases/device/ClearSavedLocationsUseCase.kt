package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class ClearSavedLocationsUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(){
        deviceRepository.clearSavedLocations()
    }
}