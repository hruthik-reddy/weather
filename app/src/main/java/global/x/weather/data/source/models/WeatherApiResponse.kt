package global.x.weather.data.source.models

data class WeatherApiResponse(
    val userId: String, val id: Int, val title: String, val body: String
)