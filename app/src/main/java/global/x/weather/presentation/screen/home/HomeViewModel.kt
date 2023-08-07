package global.x.weather.presentation.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.use_cases.weather.FetchWeatherDataUseCase
import global.x.weather.presentation.screen.home.model.WeatherData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchWeatherDataUseCase: FetchWeatherDataUseCase) : ViewModel() {
    val weatherData: MutableLiveData<WeatherData> = MutableLiveData()
    private val city: String = "Pokhara"
    val testString: MutableLiveData<String> = MutableLiveData("")

    fun onFetchWeatherData() {
        viewModelScope.launch {
            val a = fetchWeatherDataUseCase.invoke("Pokhara")
            weatherData.value = WeatherData(a.temperature, a.humidity, a.wind)
            testString.value = weatherData.value.toString()
        }
    }

}