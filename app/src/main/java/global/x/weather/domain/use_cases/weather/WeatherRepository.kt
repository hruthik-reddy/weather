package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.Outcome
import global.x.weather.domain.models.SearchResultModel
import global.x.weather.domain.models.WeatherData

interface WeatherRepository {
    suspend fun fetchWeatherData(city: String): Outcome<WeatherData.Daily>
    suspend fun fetchHourlyWeatherData(city: String, noOfDays:Int): Outcome<List<WeatherData.Daily>>

    suspend fun search(city:String): Outcome<List<SearchResultModel>>
}