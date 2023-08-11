package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class GetSecondaryCitiesUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): List<String> {
        return deviceRepository.getSecondaryCities()
    }
}