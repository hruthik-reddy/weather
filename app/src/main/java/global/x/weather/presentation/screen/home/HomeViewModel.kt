package global.x.weather.presentation.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import global.x.weather.domain.use_cases.weather.FetchWeatherDataUseCase
import global.x.weather.presentation.screen.home.model.WeatherData
import kotlinx.coroutines.launch

class HomeViewModel(private val fetchWeatherDataUseCase: FetchWeatherDataUseCase) : ViewModel() {
    val weatherData: MutableLiveData<WeatherData> = MutableLiveData()
    private val city: String = "Pokhara"

    fun onFetchWeatherData() {
        viewModelScope.launch {
            val a = fetchWeatherDataUseCase.invoke("Pokhara")
            weatherData.value = WeatherData(a.temperature, a.humidity, a.wind)
        }
    }

}