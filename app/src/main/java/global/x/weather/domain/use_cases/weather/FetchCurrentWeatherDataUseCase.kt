package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.Outcome
import global.x.weather.domain.models.WeatherData
import javax.inject.Inject

class FetchCurrentWeatherDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): Outcome<WeatherData.Daily> {
        return weatherRepository.fetchWeatherData(city)

    }

}