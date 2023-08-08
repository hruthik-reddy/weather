package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.models.WeatherDomainModel
import javax.inject.Inject

class FetchWeatherDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): WeatherDomainModel {
        val data = weatherRepository.fetchWeatherData(city)
        return if(data.isEmpty()){
            WeatherDomainModel(temperature = 0f, humidity = 0f, wind = 0f )
        } else {
            WeatherDomainModel(temperature = 1f, humidity = 1f, wind = 1f)
        }
    }

}