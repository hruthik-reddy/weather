package global.x.weather.domain.use_cases.device

import javax.inject.Inject

class GetDeviceRegionUseCase @Inject constructor(private  val repository: DeviceRepository) {
    operator fun invoke(): String {
        return repository.getDeviceRegion()
    }
}