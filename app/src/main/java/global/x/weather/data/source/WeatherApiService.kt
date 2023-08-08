package global.x.weather.data.source

import global.x.weather.data.source.models.WeatherApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeatherData(@Query("q") city: String): Response<WeatherApiResponse>
}