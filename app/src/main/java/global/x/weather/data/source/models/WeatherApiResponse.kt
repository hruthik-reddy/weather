package global.x.weather.data.source.models

data class WeatherApiResponse(
    val location: LocationApiModel,
    val current: WeatherDataApiModel
)