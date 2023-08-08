package global.x.weather.data.source

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataSource @Inject constructor() {
    companion object {
        const val TEST_BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    private val weatherApiService: WeatherApiService
    private val httpClient: OkHttpClient

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        httpClient = okHttpClientBuilder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TEST_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApiService = retrofit.create(WeatherApiService::class.java)
    }

    suspend fun fetchWeatherData(city: String): String {
        val response = weatherApiService.getWeatherData()
        return response.body()?.body ?: "Empty body"
    }
}