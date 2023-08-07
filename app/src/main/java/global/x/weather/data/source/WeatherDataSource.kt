package global.x.weather.data.source

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataSource @Inject constructor(){
    private val weatherApiService: WeatherApiService?

    init {
        weatherApiService = null //todo init with Retrofit
    }

    suspend fun fetchWeatherData(city: String): String {
        return "Test"
    }
}