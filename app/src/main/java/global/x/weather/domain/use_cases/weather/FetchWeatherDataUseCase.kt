package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.models.WeatherDomainModel

class FetchWeatherDataUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke( city:String): WeatherDomainModel{
      val testString =   weatherRepository.fetchWeatherData(city)
        return WeatherDomainModel(temperature = 23f, humidity = 20f, wind = 20f)
    }

}