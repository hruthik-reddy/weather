package global.x.weather.data.source.device

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.text.intl.Locale
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceDataSource @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        const val PREFERENCE_KEY = "device-preference-sdlk23p"
        const val PRIMARY_CITY_KEY = "primary-asdli24"
        const val SECONDARY_CITIES_KEY = "secondary-l3i430"
        const val CITY_ITEM_DELIMITER = ";"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun getPrimaryCity(): String {
        return sharedPreferences.getString(PRIMARY_CITY_KEY, "") ?: ""
    }

    fun setPrimaryCity(city: String) {
        sharedPreferences.edit().putString(PRIMARY_CITY_KEY, city).apply()
    }

    fun getSecondaryCities(): List<String> {
        with(sharedPreferences.getString(SECONDARY_CITIES_KEY, "")?.lowercase() ?: "") {
            return if (this.isNotEmpty()) this.split(";") else listOf()
        }
    }

    fun updateSecondaryCities(cities: List<String>) {
        if (getSecondaryCities().isEmpty()) {
            val sanitizedList = cities.map {
                it.lowercase()
            }.toSet()
            sharedPreferences.edit().putString(
                SECONDARY_CITIES_KEY, sanitizedList.joinToString(separator = CITY_ITEM_DELIMITER)
            ).apply()
        } else {
            val newList = cities.map {
                it.lowercase()
            }.toMutableSet()

            newList.removeIf { newCity ->
                getSecondaryCities().contains(newCity.lowercase())
            }
            newList.addAll(getSecondaryCities())
            sharedPreferences.edit().putString(
                SECONDARY_CITIES_KEY,
                newList.joinToString(separator = CITY_ITEM_DELIMITER).lowercase()
            ).apply()
        }

    }

    fun resetPrimaryCity() {
        sharedPreferences.edit().putString(PRIMARY_CITY_KEY, "").apply()
    }

    fun resetSecondaryCities() {
        sharedPreferences.edit().putString(SECONDARY_CITIES_KEY, "").apply()
    }

    fun getDeviceRegion():String{
       return TimeZone.getDefault().id
    }

    fun getSystemCurrentTimeInMillis():Long{
        return System.currentTimeMillis()
    }
}