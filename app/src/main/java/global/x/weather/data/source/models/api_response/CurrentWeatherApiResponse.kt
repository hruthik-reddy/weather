package global.x.weather.data.source.models.api_response

import global.x.weather.data.source.models.LocationApiModel
import global.x.weather.data.source.models.WeatherDetailApiModel
import global.x.weather.domain.models.WeatherData

data class CurrentWeatherApiResponse(
    val location: LocationApiModel,
    val current: WeatherDetailApiModel
) {
    fun toWeatherData(): WeatherData.Daily {
        return WeatherData.Daily(
            location = location.name,
            region = location.region,
            country = location.country,
            localTime = location.localTime,
            localTimeEpoch = location.localTimeEpoch,

            //weather data
            updatedAtEpoch = current.updatedAtEpoch,
            updatedAtTimeString = current.updatedAtTimeString,
            tempAverage = current.temperatureCentigrade,
            isDay = current.isDay == 1,
            conditionDescription = current.condition.text,
            windSpeed = current.windSpeed,
            windDegree = current.windDegree,
            precipitation = current.precipitationMillimetre,
            humidity = current.humidity,
            cloud = current.cloud,
            tempMinimum = null,
            tempMaximum = null,
            hourlyData = null
        )
    }
}