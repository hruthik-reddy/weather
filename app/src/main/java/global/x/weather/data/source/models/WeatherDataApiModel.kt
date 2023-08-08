package global.x.weather.data.source.models

import com.google.gson.annotations.SerializedName

data class WeatherDataApiModel(
    @SerializedName("last_updated_epoch")
    val updatedAtEpoch: Long,
    @SerializedName("last_updated")
    val updatedAtTimeString: String,
    @SerializedName("temp_c")
    val temperatureCentigrade: Float,
    @SerializedName("is_day")
    val isDay: Int,
    val condition: WeatherConditionApiModel,
    @SerializedName("wind_kph")
    val windSpeed: Float,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_dir")
    val windDirection: String,
    @SerializedName("precip_mm")
    val precipitationMillimetre: Float,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c")
    val feelsLikeTempCentigrade: Float
)