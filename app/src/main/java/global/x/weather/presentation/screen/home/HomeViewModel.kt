package global.x.weather.presentation.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.Outcome
import global.x.weather.domain.models.WeatherData
import global.x.weather.domain.use_cases.weather.FetchCurrentWeatherDataUseCase
import global.x.weather.domain.use_cases.weather.FetchHourlyForecastDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCurrentWeatherDataUseCase: FetchCurrentWeatherDataUseCase,
    private val fetchHourlyForecastDataUseCase: FetchHourlyForecastDataUseCase
) : ViewModel() {
    val currentWeatherData: MutableLiveData<WeatherData.Daily> = MutableLiveData()
    val forecastedWeatherData: MutableLiveData<List<WeatherData.Daily>> = MutableLiveData()
    val testString: MutableLiveData<String> = MutableLiveData("")

    fun onFetchCurrentWeatherData() {
        viewModelScope.launch {
            val response = fetchCurrentWeatherDataUseCase.invoke("Pokhara")
            if (response is Outcome.Success) {
                currentWeatherData.value = response.data
                currentWeatherData.let {
                    it.value?.let {
                        testString.value = it.localTimeEpoch.toString()
                    }
                }
            }
        }
    }

    fun onFetchForecastedWeatherData() {
        viewModelScope.launch {
            val response = fetchHourlyForecastDataUseCase.invoke("Pokhara", 3)
            if (response is Outcome.Success) {
                forecastedWeatherData.value = response.data
                forecastedWeatherData.let {
                    it.value?.let {
                        testString.value = it.size.toString()
                    }
                }
            }
        }
    }

}