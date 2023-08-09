package global.x.weather.presentation.screen.weather_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import global.x.weather.domain.use_cases.weather.FetchCurrentWeatherDataUseCase
import kotlinx.coroutines.launch

class WeatherDetailViewModel(private val fetchCurrentWeatherDataUseCase: FetchCurrentWeatherDataUseCase): ViewModel() {
    fun fetchWeatherData(){
        viewModelScope.launch {
            fetchCurrentWeatherDataUseCase.invoke("Pokhara")
        }
    }
}