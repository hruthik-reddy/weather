package global.x.weather.domain.use_cases.weather

interface WeatherRepository {
    fun fetchWeatherData(city: String): String
}