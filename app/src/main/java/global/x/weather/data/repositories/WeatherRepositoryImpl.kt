package global.x.weather.data.repositories

import global.x.weather.data.source.WeatherDataSource
import global.x.weather.domain.use_cases.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) : WeatherRepository {
    override suspend fun fetchWeatherData(city: String): String {
        return weatherDataSource.fetchWeatherData(city)
    }
}