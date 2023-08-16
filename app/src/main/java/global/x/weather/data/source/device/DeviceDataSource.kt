package global.x.weather.data.source.device

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import global.x.weather.data.source.device.model.SavedLocationModel
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceDataSource @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        const val PREFERENCE_KEY = "devids13ce-p24refer1212ence-sdlk23p"
        const val SAVED_LOCATION_KEY = "s3oaved-12locu4atio3n-lj2k430asd"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun updateSavedLocations(locations: List<SavedLocationModel?>) {
        val locationsSet = locations.toSet()
        val converter = Gson()
        sharedPreferences.edit().putString(
            SAVED_LOCATION_KEY, converter.toJson(locationsSet)
        ).apply()
    }

    fun getSavedLocations(): List<SavedLocationModel?> {
        val stringSet = sharedPreferences.getStringSet(SAVED_LOCATION_KEY, null)
        val gsonConverter = Gson()
        stringSet?.let { setItem ->
            if (setItem.isNotEmpty()) {
                return setItem.map {
                    it?.let {
                        gsonConverter.fromJson(it, SavedLocationModel::class.java)
                    }
                }

            }
        }
        return emptyList()
    }

    fun addSavedLocations(locations: List<SavedLocationModel?>) {
        val gsonConverter = Gson()
        val newSet = getSavedLocations().toSet().toMutableSet().also {
            it.addAll(locations)
        }
        val newList = newSet.map {
            gsonConverter.toJson(it)
        }.toSet()
        sharedPreferences.edit().putStringSet(SAVED_LOCATION_KEY, newList).apply()
    }

    fun deleteSavedLocations(locations: List<SavedLocationModel?>) {
        val updatedList = getSavedLocations().toMutableList().also {
            it.removeIf { locationModel ->
                locations.contains(locationModel)
            }
        }
        addSavedLocations(updatedList)
    }

    fun clearSavedLocations() {
        sharedPreferences.edit().putString(SAVED_LOCATION_KEY, "").apply()
    }

    fun getDefaultSavedLocation(): SavedLocationModel? {
        return getSavedLocations().firstOrNull { locationModel ->
            locationModel?.isDefault ?: false
        }
    }


    fun getDeviceRegion(): String {
        return TimeZone.getDefault().id
    }

    fun getSystemCurrentTimeInMillis(): Long {
        return System.currentTimeMillis()
    }
}