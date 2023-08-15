package global.x.weather.presentation.screen.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import global.x.weather.R
import global.x.weather.infrastructure.util.DateUtil
import global.x.weather.presentation.framework.components.CenterContentTopAppBar
import global.x.weather.presentation.framework.components.LargeHorizontalSpacer
import global.x.weather.presentation.framework.components.MediumHorizontalSpacer
import global.x.weather.presentation.framework.components.SimpleHumidity
import global.x.weather.presentation.framework.components.SimpleHumidityStat
import global.x.weather.presentation.framework.components.SimpleRain
import global.x.weather.presentation.framework.components.SimpleRainStat
import global.x.weather.presentation.framework.components.SimpleTemperature
import global.x.weather.presentation.framework.components.SimpleTemperatureStat
import global.x.weather.presentation.framework.components.SimpleWeatherStat
import global.x.weather.presentation.framework.components.SmallHorizontalSpacer
import global.x.weather.presentation.framework.components.SmallVerticalSpacer
import global.x.weather.presentation.framework.components.TinyHorizontalSpacer
import global.x.weather.presentation.framework.components.TinyVerticalSpacer
import global.x.weather.presentation.framework.components.XLargeVerticalSpacer
import global.x.weather.presentation.screen.favorites.model.FavoriteLocationModel
import global.x.weather.presentation.screen.home.model.WeatherData
import java.util.Locale

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    onBackIconClicked: () -> Unit,
    dateState: State<String?>,
    favoriteLocationDataList: State<List<FavoriteLocationModel>?>,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        CenterContentTopAppBar(
            title = { Text(stringResource(id = R.string.title_favorites)) },
            startIcon = ImageVector.vectorResource(id = R.drawable.ic_back),
            onStartIconClicked = onBackIconClicked
        )
        SmallVerticalSpacer()
        Content(
            dateState = dateState,
            favoriteLocationDataList = favoriteLocationDataList,
            onFavoriteItemTapped = onFavoriteItemTapped
        )

    }
}

@Composable
@Preview
private fun FavoriteScreenPreview() {
    FavoriteScreen(
        onBackIconClicked = {},
        dateState = remember {
            mutableStateOf("Jan 14th")
        },
        favoriteLocationDataList = remember {
            mutableStateOf(
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
            )

        },
        onFavoriteItemTapped = {},
        paddingValues = PaddingValues(4.dp)
    )
}


@Composable
private fun Content(
    dateState: State<String?>,
    favoriteLocationDataList: State<List<FavoriteLocationModel>?>,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                contentDescription = stringResource(
                    id = R.string.content_description_date
                )
            )
            TinyHorizontalSpacer()
            Text(dateState.value ?: "")
        }

        XLargeVerticalSpacer()

        favoriteLocationDataList.value?.forEach {
            Column() {
                FavoriteLocationItem(data = it, onFavoriteItemTapped = onFavoriteItemTapped)
                TinyVerticalSpacer()
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteLocationItem(
    data: FavoriteLocationModel,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Surface(onClick = { onFavoriteItemTapped(data) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.Green),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(data.getDisplayName())
            Row(verticalAlignment = Alignment.CenterVertically) {
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_centigrade),
                        data.weatherData?.temperature ?: 0f
                    ),
                    icon = { SimpleTemperature() },
                    title = ""
                )
                MediumHorizontalSpacer()
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_percent),
                        data.weatherData?.humidity ?: 0f
                    ),
                    icon = { SimpleHumidity() },
                    title = ""
                )
                MediumHorizontalSpacer()
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_percent),
                        data.weatherData?.precipitation ?: 0f
                    ),
                    icon = { SimpleRain() },
                    title = ""
                )
            }

        }
    }

}
