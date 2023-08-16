package global.x.weather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.models.FavoriteLocationModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val onNavigateToSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val onNavigateToFavorites: MutableLiveData<Boolean> = MutableLiveData(false)
    val onNavigateBack: MutableLiveData<Boolean> = MutableLiveData(false)
    val onShowWeatherDetail: MutableLiveData<FavoriteLocationModel> = MutableLiveData()

    init {
        onShowWeatherDetail(
            FavoriteLocationModel(
                id = 0,
                name = "Pokhara",
                country = "Nepal",
                region = "",
                weatherData = null
            )
        )
    }


    fun onNavigateToSearch() {
        onNavigateToSearch.value = true
    }

    fun onNavigateToSearchResolved() {
        onNavigateToSearch.value = false
    }

    fun onNavigateToFavorites() {
        onNavigateToFavorites.value = true
    }

    fun onNavigateToFavoritesResolved() {
        onNavigateToFavorites.value = false
    }

    fun onNavigateBack() {
        onNavigateBack.value = true
    }

    fun onNavigateBackResolved() {
        onNavigateBack.value = false
    }

    fun onShowWeatherDetail(locationModel: FavoriteLocationModel) {
        onShowWeatherDetail.value = locationModel

    }

    fun onShowWeatherDetailResolved() {
        onShowWeatherDetail.value = null
    }


}