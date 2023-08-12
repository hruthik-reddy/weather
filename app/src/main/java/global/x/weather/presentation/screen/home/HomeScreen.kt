package global.x.weather.presentation.screen.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import global.x.weather.R
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
import global.x.weather.presentation.framework.components.SimpleWindStat
import global.x.weather.presentation.framework.components.TinyHorizontalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFetchCurrentWeather: () -> Unit,
    onFetchForecastedWeather: () -> Unit,
    paddingValues: PaddingValues,
    textState: State<String?>
) {
    Scaffold(
        modifier = Modifier.padding(paddingValues)
    ) {
        Screen(
            onFetchCurrentWeather = onFetchCurrentWeather,
            onFetchForecastedWeather = onFetchForecastedWeather,
            paddingValues = it,
            textState = textState
        )
    }
}

@Composable
fun Screen(
    onFetchCurrentWeather: () -> Unit,
    onFetchForecastedWeather: () -> Unit,
    paddingValues: PaddingValues,
    textState: State<String?>
) {
    Column() {
        CenterContentTopAppBar(
            title = { Text("Pokhara") },
            startIcon = ImageVector.vectorResource(id = R.drawable.ic_map),
            startIconContentDescription = stringResource(id = R.string.content_description_saved_regions),
            onStartIconClicked = { /*TODO*/ },
            endIcon = ImageVector.vectorResource(id = R.drawable.ic_search),
            endIconContentDescription = stringResource(id = R.string.content_description_search_regions)
        ) {

        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            Content(
                onFetchCurrentWeather = onFetchCurrentWeather,
                onFetchForecastedWeather = onFetchForecastedWeather,
                paddingValues = paddingValues,
                textState = textState
            )
        }

    }


}

@Composable
fun Content(
    onFetchCurrentWeather: () -> Unit,
    onFetchForecastedWeather: () -> Unit,
    paddingValues: PaddingValues,
    textState: State<String?>
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
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


            Text("12 Aug")
        }
        MediumVerticalSpacer()

        //Temperature
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 38.dp)
        ) {
            Text("23", fontSize = 130.sp)
            Text(stringResource(id = R.string.degree_centigrade_placeholder), fontSize = 38.sp)
        }

        MediumVerticalSpacer()
        //weather condition
        SimpleWeatherCondition(iconResource = R.drawable.ic_cloudy, description = "Partly cloudy")
        MediumVerticalSpacer()

        //Weather stat
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            SimpleWindStat(wind = 22f)
            SimpleHumidityStat(humidity = 23f)
            SimpleRainStat(rain = 42f)
        }

        MediumVerticalSpacer()
        HourlyWeatherStat(
            items = listOf(
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2
            )
        )

        MediumVerticalSpacer()
        DailyWeatherForecast(items = listOf(1, 2, 3, 4, 5))
    }


}

@Composable
@Preview
private fun ContentPreview() {
    Content(
        onFetchCurrentWeather = { /*TODO*/ },
        onFetchForecastedWeather = { /*TODO*/ },
        paddingValues = PaddingValues(20.dp),
        textState = remember {
            mutableStateOf("test")
        }
    )
}

@Composable
fun HourlyWeatherStat(items: List<Int>) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        items.forEach {
            TinyHorizontalSpacer()
            SimpleRainStat(rain = 22f)
            TinyHorizontalSpacer()
        }
    }
}

@Composable
fun DailyWeatherForecast(items: List<Int>) {
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
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
            items.forEach() {
                Row {
                    Text("23 Aug")
                    LargeHorizontalSpacer()
                    Text(String.format(stringResource(id = R.string.quantity_centigrade), 22f))
                    MediumHorizontalSpacer()
                    Text(String.format(stringResource(id = R.string.quantity_percent), 24f))
                    MediumHorizontalSpacer()
                    Text(String.format(stringResource(id = R.string.quantity_percent), 44f))
                }
            }

        }
    }
}

@Composable
@Preview
private fun DailyWeatherForecastPreview() {
    DailyWeatherForecast(items = listOf(1, 3, 4, 5, 5))
}