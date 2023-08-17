package global.x.weather.presentation.screen.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import global.x.weather.R
import global.x.weather.domain.models.WeatherData
import global.x.weather.infrastructure.util.DateUtil
import global.x.weather.presentation.framework.components.CenterContentTopAppBar
import global.x.weather.presentation.framework.components.LargeHorizontalSpacer
import global.x.weather.presentation.framework.components.LargeVerticalSpacer
import global.x.weather.presentation.framework.components.MediumHorizontalSpacer
import global.x.weather.presentation.framework.components.MediumVerticalSpacer
import global.x.weather.presentation.framework.components.SimpleHumidity
import global.x.weather.presentation.framework.components.SimpleHumidityStat
import global.x.weather.presentation.framework.components.SimpleRain
import global.x.weather.presentation.framework.components.SimpleRainStat
import global.x.weather.presentation.framework.components.SimpleTemperature
import global.x.weather.presentation.framework.components.SimpleWeatherCondition
import global.x.weather.presentation.framework.components.SimpleWeatherStat
import global.x.weather.presentation.framework.components.SimpleWindStat
import global.x.weather.presentation.framework.components.TinyHorizontalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    weatherDataState: State<List<WeatherData.Daily>?>,
    paddingValues: PaddingValues,
    onAppBarStartIconTapped: () -> Unit,
    onAppBarEndIconTapped: () -> Unit,
    appbarStartIcon: ImageVector,
    appbarEndIcon: ImageVector? = null
) {
    Scaffold(
        modifier = Modifier.padding(paddingValues)
    ) {
        Screen(
            weatherDataState = weatherDataState,
            paddingValues = it,
            onAppBarEndIconTapped = onAppBarEndIconTapped,
            onAppbarStartIconTapped = onAppBarStartIconTapped,
            appbarStartIcon = appbarStartIcon,
            appbarEndIcon = appbarEndIcon

        )
    }
}

@Composable
fun Screen(
    weatherDataState: State<List<WeatherData.Daily>?>,
    paddingValues: PaddingValues,
    onAppBarEndIconTapped: () -> Unit,
    onAppbarStartIconTapped: () -> Unit,
    appbarStartIcon: ImageVector,
    appbarEndIcon: ImageVector?
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        CenterContentTopAppBar(
            title = { Text(weatherDataState.value?.firstOrNull()?.location ?: "") },
            startIcon = appbarStartIcon,
            onStartIconClicked = onAppbarStartIconTapped,
            endIcon = appbarEndIcon,
            onEndIconClicked = onAppBarEndIconTapped
        )
        Content(
            weatherDataState = weatherDataState,
        )

    }
}

@Composable
fun Content(
    weatherDataState: State<List<WeatherData.Daily>?>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediumVerticalSpacer()
        //Date
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
            weatherDataState.value?.let {
                Text(DateUtil.getFormattedDateMonth(it[0].localTime))
            }
        }
        MediumVerticalSpacer()

        //Temperature
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 38.dp)
        ) {
            weatherDataState.value?.let {
                Text(it[0].tempAverage.toString(), fontSize = 130.sp)
                Text(stringResource(id = R.string.degree_centigrade_placeholder), fontSize = 38.sp)
            }
        }

        MediumVerticalSpacer()
        //weather condition
        weatherDataState.value?.let {
            SimpleWeatherCondition(
                iconResource = R.drawable.ic_cloudy,
                description = it[0].conditionDescription
            )
        }

        MediumVerticalSpacer()

        //Weather stat
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            weatherDataState.value?.let {
                SimpleWindStat(wind = it[0].windSpeed)
                SimpleHumidityStat(humidity = it[0].humidity)
                SimpleRainStat(rain = it[0].precipitation)
            }

        }

        MediumVerticalSpacer()
        weatherDataState.value?.let {
            HourlyWeatherStat(
                items = it[0].hourlyData ?: mutableListOf()

            )
        }


        MediumVerticalSpacer()
        weatherDataState.value?.let {
            DailyWeatherForecast(items = it)

        }
    }

}


@Composable
fun HourlyWeatherStat(items: List<WeatherData.Hourly>) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        items.forEach {
            TinyHorizontalSpacer()
            SimpleWeatherStat(
                title = DateUtil.getFormattedDateTime(it.time),
                icon = { SimpleRain() },
                stat = "${it.temp}°"
            )
            TinyHorizontalSpacer()
        }
    }
}

@Composable
fun DailyWeatherForecast(items: List<WeatherData.Daily?>) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                contentDescription = stringResource(
                    id = R.string.content_description_none
                )
            )
            LargeHorizontalSpacer()
            SimpleTemperature()
            MediumHorizontalSpacer()
            SimpleHumidity()
            MediumHorizontalSpacer()
            SimpleRain()
        }
        LargeVerticalSpacer()
        Column() {
            items.forEach {
                it?.let {
                    Row {
                        Text(DateUtil.getFormattedDateMonthShort(it.date))
                        LargeHorizontalSpacer()
                        Text(
                            String.format(
                                stringResource(id = R.string.quantity_centigrade),
                                it.tempAverage
                            )
                        )
                        MediumHorizontalSpacer()
                        Text(
                            String.format(
                                stringResource(id = R.string.quantity_percent),
                                it.humidity
                            )
                        )
                        MediumHorizontalSpacer()
                        Text(
                            String.format(
                                stringResource(id = R.string.quantity_percent),
                                it.precipitation
                            )
                        )
                    }
                }

            }

        }
    }
}
