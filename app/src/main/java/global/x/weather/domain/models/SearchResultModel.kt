package global.x.weather.domain.models

data class SearchResultModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
) {
    fun getDisplayName(): String {
        return if (region.isBlank()) {
            "$name, $country"
        } else {
            "$name, $region, $country"
        }
    }
}