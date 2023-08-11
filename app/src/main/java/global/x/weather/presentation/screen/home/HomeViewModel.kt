package global.x.weather.presentation.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.Outcome
import global.x.weather.domain.models.WeatherData
import global.x.weather.domain.use_cases.device.GetPrimaryCityUseCase
import global.x.weather.domain.use_cases.device.GetSecondaryCitiesUseCase
import global.x.weather.domain.use_cases.device.ResetPrimaryCityUseCase
import global.x.weather.domain.use_cases.device.ResetSecondaryCityUseCase
import global.x.weather.domain.use_cases.device.SetPrimaryCityUseCase
import global.x.weather.domain.use_cases.device.UpdateSecondaryCitiesUseCase
import global.x.weather.domain.use_cases.weather.FetchCurrentWeatherDataUseCase
import global.x.weather.domain.use_cases.weather.FetchHourlyForecastDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCurrentWeatherDataUseCase: FetchCurrentWeatherDataUseCase,
    private val fetchHourlyForecastDataUseCase: FetchHourlyForecastDataUseCase,
    private val setPrimaryCityUseCase: SetPrimaryCityUseCase,
    private val updateSecondaryCitiesUseCase: UpdateSecondaryCitiesUseCase,
    private val getPrimaryCityUseCase: GetPrimaryCityUseCase,
    private val resetPrimaryCityUseCase: ResetPrimaryCityUseCase,
    private val resetSecondaryCityUseCase: ResetSecondaryCityUseCase,
    private val getSecondaryCitiesUseCase: GetSecondaryCitiesUseCase
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