package global.x.weather.data.source

import global.x.weather.data.source.models.WeatherApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    @GET("/posts/1")
    suspend fun getWeatherData(): Response<WeatherApiResponse>
}