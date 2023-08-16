package global.x.weather.presentation.screen.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import global.x.weather.domain.models.FavoriteLocationModel
import global.x.weather.domain.use_cases.device.GetSavedLocationsUseCase
import global.x.weather.domain.use_cases.device.GetSystemCurrentTimeInMillisUseCase
import global.x.weather.infrastructure.util.DateUtil
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    getSavedLocationsUseCase: GetSavedLocationsUseCase,
) :
    ViewModel() {
    val dateState: MutableLiveData<String> = MutableLiveData("")

    val favoriteLocationDataList: MutableLiveData<List<FavoriteLocationModel>> =
        MutableLiveData(mutableListOf())

    init {
        dateState.value = DateUtil.getDateFromEpoch(getSystemCurrentTimeInMillisUseCase.invoke())
        favoriteLocationDataList.value = getSavedLocationsUseCase.invoke()
    }

    fun onFavoriteItemTapped(data: FavoriteLocationModel) {

    }
}