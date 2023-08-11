package global.x.weather.domain.use_cases.device

interface DeviceRepository {
    fun getPrimaryCity(): String
    fun setPrimaryCity(city: String)
    fun getSecondaryCities(): List<String>
    fun updateSecondaryCities(cities: List<String>)
    fun resetPrimaryCity()
    fun resetSecondaryCities()
}