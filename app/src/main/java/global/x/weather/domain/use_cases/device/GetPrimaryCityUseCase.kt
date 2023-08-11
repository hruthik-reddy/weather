package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class GetPrimaryCityUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): String {
        return deviceRepository.getPrimaryCity()
    }

}