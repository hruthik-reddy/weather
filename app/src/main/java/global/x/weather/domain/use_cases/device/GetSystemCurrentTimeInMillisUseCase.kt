package global.x.weather.domain.use_cases.device

import global.x.weather.data.source.device.DeviceDataSource
import javax.inject.Inject

class GetSystemCurrentTimeInMillisUseCase @Inject constructor(private val deviceDataSource: DeviceDataSource) {
    operator fun invoke(): Long {
        return deviceDataSource.getSystemCurrentTimeInMillis()
    }
}