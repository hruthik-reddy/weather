package global.x.weather.data.source

import androidx.compose.runtime.Composable

interface WeatherApiService {
//    @GET("/weather")
    suspend fun getWeatherData(city:String): Response<String>
}