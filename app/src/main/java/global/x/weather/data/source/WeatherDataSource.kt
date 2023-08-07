package global.x.weather.data.source

class WeatherDataSource {
    private val weatherApiService: WeatherApiService?

    init {
        weatherApiService = null //todo init with Retrofit
    }

    suspend fun fetchWeatherData(city: String): String {
        return "Test"
    }
}