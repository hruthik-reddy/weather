package global.x.weather.domain.use_cases.weather

import global.x.weather.domain.Outcome
import global.x.weather.domain.models.SearchResultModel
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): Outcome<List<SearchResultModel>> {
        return weatherRepository.search(city)
    }
}