package global.x.weather.presentation.screen.weather_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.Outcome
import global.x.weather.domain.use_cases.device.GetDeviceRegionUseCase
import global.x.weather.domain.use_cases.device.GetSystemCurrentTimeInMillisUseCase
import global.x.weather.domain.use_cases.weather.FetchCurrentWeatherDataUseCase
import global.x.weather.domain.use_cases.weather.FetchHourlyForecastDataUseCase
import global.x.weather.infrastructure.util.toFavoriteLocationModel
import global.x.weather.presentation.screen.WeatherNavigationRoute
import global.x.weather.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchHourlyForecastDataUseCase: FetchHourlyForecastDataUseCase,
    getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    deviceRegionUseCase: GetDeviceRegionUseCase

) : HomeViewModel(
    savedStateHandle,
    fetchHourlyForecastDataUseCase,
    getSystemCurrentTimeInMillisUseCase,
    deviceRegionUseCase
) {

    init {
        fetchWeatherDetail()
    }

    private fun fetchWeatherDetail() {

        val locationString: String =
            savedStateHandle[WeatherNavigationRoute.SearchResult.argumentName] ?: ""

        val location = locationString.toFavoriteLocationModel()
        viewModelScope.launch {
            val response =
                fetchHourlyForecastDataUseCase.invoke(location.getDisplayName(), 3)
            if (response is Outcome.Success) {
                filterForecastedWeatherResult(response.data)
            }
        }
    }

}