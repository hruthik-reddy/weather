package global.x.weather.domain.use_cases.weather

interface WeatherRepository {
    suspend fun fetchWeatherData(city: String): String
}