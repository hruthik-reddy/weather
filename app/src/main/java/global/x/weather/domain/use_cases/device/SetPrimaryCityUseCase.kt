package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class SetPrimaryCityUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(city: String) {
        deviceRepository.setPrimaryCity(city)
    }
}