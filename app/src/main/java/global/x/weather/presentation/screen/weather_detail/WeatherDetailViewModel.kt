package global.x.weather.presentation.screen.weather_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.R
import global.x.weather.domain.Outcome
import global.x.weather.domain.models.FavoriteLocationModel
import global.x.weather.domain.use_cases.device.DeleteSavedLocationUseCase
import global.x.weather.domain.use_cases.device.GetDefaultSavedLocationUseCase
import global.x.weather.domain.use_cases.device.GetDeviceRegionUseCase
import global.x.weather.domain.use_cases.device.GetSavedLocationsUseCase
import global.x.weather.domain.use_cases.device.GetSystemCurrentTimeInMillisUseCase
import global.x.weather.domain.use_cases.device.UpdateSavedLocationsUseCase
import global.x.weather.domain.use_cases.weather.FetchHourlyForecastDataUseCase
import global.x.weather.domain.use_cases.weather.SearchCityUseCase
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
    deviceRegionUseCase: GetDeviceRegionUseCase,
    getSavedLocationsUseCase: GetSavedLocationsUseCase,
    getDefaultSavedLocationUseCase: GetDefaultSavedLocationUseCase,
    updateSavedLocationsUseCase: UpdateSavedLocationsUseCase,
    val deleteSavedLocationUseCase: DeleteSavedLocationUseCase,

    searchCityUseCase: SearchCityUseCase

) : HomeViewModel(
    savedStateHandle,
    fetchHourlyForecastDataUseCase,
    getSystemCurrentTimeInMillisUseCase,
    deviceRegionUseCase,
    getDefaultSavedLocationUseCase,
    getSavedLocationsUseCase,
    searchCityUseCase,
    updateSavedLocationsUseCase
) {

    private lateinit var favoriteLocationModel: FavoriteLocationModel

    init {
        fetchWeatherDetail()
    }

    val favoriteIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.ic_heart)

    private fun fetchWeatherDetail() {

        val locationString: String =
            savedStateHandle[WeatherNavigationRoute.SearchResult.argumentName] ?: ""

        favoriteLocationModel = locationString.toFavoriteLocationModel()
        viewModelScope.launch {
            val response =
                fetchHourlyForecastDataUseCase.invoke(favoriteLocationModel.getDisplayName(), 5)
            if (response is Outcome.Success) {
                filterForecastedWeatherResult(response.data)
                checkIfIsFavorite(response.data.firstOrNull()?.getIdentifier() ?: "")
            }
        }
    }

    private fun checkIfIsFavorite(locationIdentifier: String) {
        val currentlySavedLocations = getSavedLocationsUseCase.invoke()
        currentlySavedLocations.firstOrNull() {
            it.getIdentifier() == locationIdentifier
        }.apply {
            if (this != null) {
                favoriteIcon.value = R.drawable.ic_heart_filled
            } else {
                favoriteIcon.value = R.drawable.ic_heart
            }
        }
    }

    fun onSaveLocationTapped() {
        val currentlySavedLocations = getSavedLocationsUseCase.invoke()
        currentlySavedLocations.firstOrNull() { locationModel ->
            locationModel.getIdentifier() == favoriteLocationModel.getIdentifier()
        }.apply {
            if (this == null) {
                updateSavedLocationsUseCase.invoke(listOf(favoriteLocationModel))
            } else {
                deleteSavedLocationUseCase.invoke(listOf(favoriteLocationModel))
            }
            checkIfIsFavorite(favoriteLocationModel.getIdentifier())

        }
    }

}