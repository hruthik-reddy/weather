package global.x.weather.data.source.weather.models.api_response

import global.x.weather.data.source.weather.models.DailyForecastApiModel
import global.x.weather.data.source.weather.models.LocationApiModel
import global.x.weather.data.source.weather.models.WeatherDetailApiModel
import global.x.weather.domain.models.WeatherData

data class DailyForecastApiResponse(
    val location: LocationApiModel,
    val current: WeatherDetailApiModel,
    val forecast: DailyForecastApiModel
) {
    fun toForecastData(): List<WeatherData.Daily> {
        return forecast.dailyForecastData.map { dailyForecast ->

            WeatherData.Daily(
                location = location.name,
                region = location.region,
                country = location.country,
                localTime = location.localTime,
                localTimeEpoch = location.localTimeEpoch,

                //weather data
                updatedAtEpoch = current.updatedAtEpoch,
                updatedAtTimeString = current.updatedAtTimeString,
                tempAverage = dailyForecast.dailySummary.avgTemperatureCentigrade,

                isDay = current.isDay == 1,
                conditionDescription = current.condition.text,
                windSpeed = current.windSpeed,
                windDegree = current.windDegree,
                precipitation = current.precipitationMillimetre,
                humidity = current.humidity,
                cloud = current.cloud,
                hourlyData = dailyForecast.hourlyForecastDataList.map { hourlyForecast ->
                    WeatherData.Hourly(
                        location = location.name,
                        region = location.region,
                        country = location.country,
                        localTime = location.localTime,
                        localTimeEpoch = location.localTimeEpoch,
                        timeEpoch = hourlyForecast.timeEpoch,
                        time = hourlyForecast.time,
                        temp = hourlyForecast.temperatureCentigrade,
                        isDay = hourlyForecast.isDay == 1,
                        conditionDescription = hourlyForecast.condition.text,
                        windSpeed = hourlyForecast.windSpeed,
                        windDegree = hourlyForecast.windDegree,
                        windDirection = hourlyForecast.windDirection,
                        precipitation = hourlyForecast.precipitationMillimetre,
                        humidity = hourlyForecast.humidity,
                        cloud = hourlyForecast.cloud,
                        rainingChance = hourlyForecast.rainingChance,
                        snowingChance = hourlyForecast.snowingChance
                    )
                },
                tempMaximum = null,
                tempMinimum = null,
                )
        }
    }
}




