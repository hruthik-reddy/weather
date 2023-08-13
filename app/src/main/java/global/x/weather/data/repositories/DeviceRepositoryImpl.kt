package global.x.weather.data.repositories

import global.x.weather.data.source.device.DeviceDataSource
import global.x.weather.domain.use_cases.device.DeviceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepositoryImpl @Inject constructor(private val deviceDataSource: DeviceDataSource) :
    DeviceRepository {
    override fun getPrimaryCity(): String {
        return deviceDataSource.getPrimaryCity()

    }

    override fun setPrimaryCity(city: String) {
        deviceDataSource.setPrimaryCity(city)
    }

    override fun getSecondaryCities(): List<String> {
        return deviceDataSource.getSecondaryCities()
    }


    override fun updateSecondaryCities(cities: List<String>) {
        deviceDataSource.updateSecondaryCities(cities)
    }

    override fun resetPrimaryCity() {
        deviceDataSource.resetPrimaryCity()
    }

    override fun resetSecondaryCities() {
        deviceDataSource.resetSecondaryCities()
    }

    override fun getDeviceRegion(): String {
        return deviceDataSource.getDeviceRegion()
    }

    override fun getSystemCurrentTimeInMillis(): Long {
        return deviceDataSource.getSystemCurrentTimeInMillis()
    }
}
