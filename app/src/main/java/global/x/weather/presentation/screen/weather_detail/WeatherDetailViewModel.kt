package global.x.weather.presentation.screen.weather_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import global.x.weather.domain.use_cases.weather.FetchWeatherDataUseCase
import kotlinx.coroutines.launch

class WeatherDetailViewModel(private val fetchWeatherDataUseCase: FetchWeatherDataUseCase): ViewModel() {
    fun fetchWeatherData(){
        viewModelScope.launch {
            fetchWeatherDataUseCase.invoke("Pokhara")
        }
    }
}