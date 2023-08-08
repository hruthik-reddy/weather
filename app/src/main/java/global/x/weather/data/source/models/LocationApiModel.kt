package global.x.weather.data.source.models

import com.google.gson.annotations.SerializedName

data class LocationApiModel(
    val name:String,
    val region: String,
    val country: String,
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long
)