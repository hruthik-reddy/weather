package global.x.weather.presentation.screen.favorites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.use_cases.device.GetSystemCurrentTimeInMillisUseCase
import global.x.weather.infrastructure.util.DateUtil
import global.x.weather.domain.models.FavoriteLocationModel
import global.x.weather.presentation.screen.home.model.WeatherData
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase) :
    ViewModel() {
    val dateState: MutableLiveData<String> = MutableLiveData("")

    val favoriteLocationDataList: MutableLiveData<List<FavoriteLocationModel>?> =
        MutableLiveData(mutableListOf())

    init {
        dateState.value = DateUtil.getDateFromEpoch(getSystemCurrentTimeInMillisUseCase.invoke())
        favoriteLocationDataList.value =
            listOf(
                FavoriteLocationModel(
                    0,
                    "Tokyo",
                    region = "Asia",
                    country = "Japan",
                    isDefault = true,
                    weatherData = WeatherData(20f, 21f, 24f, 33f)
                ),
                FavoriteLocationModel(
                    0,
                    "Pokhara",
                    region = "Asia",
                    country = "Nepal",
                    isDefault = false,
                    weatherData = WeatherData(30f, 51f, 44f, 32f)
                ),
                FavoriteLocationModel(
                    0,
                    "Jakarta",
                    region = "Asia",
                    country = "Indonesia",
                    isDefault = false,
                    weatherData = WeatherData(20f, 21f, 14f, 3f)
                ),
                FavoriteLocationModel(
                    0,
                    "Bangkok",
                    region = "Asia",
                    country = "Thailand",
                    isDefault = false,
                    weatherData = WeatherData(10f, 61f, 34f, 43f)
                )
            )

    }

    fun onFavoriteItemTapped(data: FavoriteLocationModel) {
        Log.e("FavoriteVoiewModel", "Item tapped")
    }

}