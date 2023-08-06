package global.x.weather.domain.use_cases.weather

class FetchWeatherDataUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke( city:String): String{
      return  weatherRepository.fetchWeatherData(city)
    }

}