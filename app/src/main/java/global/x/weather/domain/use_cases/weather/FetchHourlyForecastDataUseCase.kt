package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.Outcome
import global.x.weather.domain.models.WeatherData
import javax.inject.Inject

class FetchHourlyForecastDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String, noOfDays: Int): Outcome<List<WeatherData.Daily>> {
        return weatherRepository.fetchHourlyWeatherData(city, noOfDays)

    }
}