package global.x.weather.presentation.screen.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.Outcome
import global.x.weather.domain.models.FavoriteLocationModel
import global.x.weather.domain.use_cases.device.GetSavedLocationsUseCase
import global.x.weather.domain.use_cases.device.GetSystemCurrentTimeInMillisUseCase
import global.x.weather.domain.use_cases.weather.FetchHourlyForecastDataUseCase
import global.x.weather.infrastructure.util.DateUtil
import global.x.weather.presentation.screen.home.model.WeatherData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
     getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    val forecastDataUseCase: FetchHourlyForecastDataUseCase,
) : ViewModel() {
    val dateState: MutableLiveData<String> = MutableLiveData("")

    val favoriteLocationDataList: MutableLiveData<List<FavoriteLocationModel>> =
        MutableLiveData(mutableListOf())

    init {
        dateState.value = DateUtil.getDateFromEpoch(getSystemCurrentTimeInMillisUseCase.invoke())
        refreshFavoriteData()
    }

    fun onFavoriteItemClicked(data: FavoriteLocationModel) {

    }

    private fun refreshFavoriteData() {
        if (getSavedLocationsUseCase.invoke().isEmpty()) return
        favoriteLocationDataList.value = getSavedLocationsUseCase.invoke()
        fetchWeatherData()

    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            getSavedLocationsUseCase.invoke().forEach {
                when (val forecastData = forecastDataUseCase.invoke(it.name, 3)) {
                    is Outcome.Success -> {
                        val currentList = favoriteLocationDataList.value
                        val newList = currentList?.map { favoriteLocationModel ->
                            if (forecastData.data.firstOrNull()
                                    ?.getIdentifier() == favoriteLocationModel.getIdentifier()
                            ) {
                                return@map FavoriteLocationModel(
                                    id = favoriteLocationModel.id,
                                    name = favoriteLocationModel.name,
                                    region = favoriteLocationModel.region,
                                    country = favoriteLocationModel.country,
                                    weatherData = WeatherData(
                                        precipitation = forecastData.data.firstOrNull()?.precipitation
                                            ?: 0f,
                                        wind = forecastData.data.firstOrNull()?.windSpeed ?: 0f,
                                        humidity = forecastData.data.firstOrNull()?.humidity ?: 0f,
                                        temperature = forecastData.data.firstOrNull()?.tempAverage
                                            ?: 0f
                                    )
                                )

                            } else favoriteLocationModel

                        }
                        favoriteLocationDataList.value = newList
                    }

                    is Outcome.Error -> {

                    }

                }
            }

        }

    }
}