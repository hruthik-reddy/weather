package global.x.weather.data.repositories

import global.x.weather.data.source.device.DeviceDataSource
import global.x.weather.data.source.device.model.SavedLocationModel
import global.x.weather.domain.use_cases.device.DeviceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepositoryImpl @Inject constructor(private val deviceDataSource: DeviceDataSource) :
    DeviceRepository {
    override fun getSavedLocations(): List<SavedLocationModel> {
        return deviceDataSource.getSavedLocations()
    }

    override fun updateSavedLocations(locations: List<SavedLocationModel>) {
         deviceDataSource.updateSavedLocations(locations = locations)
    }

    override fun deleteSavedLocation(locations: List<SavedLocationModel>) {
        deviceDataSource.deleteSavedLocations(locations = locations)
    }

    override fun clearSavedLocations() {
        deviceDataSource.clearSavedLocations()
    }

    override fun getDefaultSavedLocation(): SavedLocationModel? {
       return deviceDataSource.getDefaultSavedLocation()
    }

    override fun getDeviceRegion(): String {
        return deviceDataSource.getDeviceRegion()
    }

    override fun getSystemCurrentTimeInMillis(): Long {
        return deviceDataSource.getSystemCurrentTimeInMillis()
    }
}
