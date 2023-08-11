package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class UpdateSecondaryCitiesUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(cities: List<String>) {
        deviceRepository.updateSecondaryCities(cities)
    }
}