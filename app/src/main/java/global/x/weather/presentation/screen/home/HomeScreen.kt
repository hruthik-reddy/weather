package global.x.weather.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    Content(
        onFetchCurrentWeather = onFetchCurrentWeather,
        onFetchForecastedWeather = onFetchForecastedWeather,
        paddingValues = paddingValues,
        textState = textState
    )

}

@Composable
fun Content(
    onFetchCurrentWeather: () -> Unit,
    onFetchForecastedWeather: () -> Unit,
    paddingValues: PaddingValues,
    textState: State<String?>
) {


    Column(verticalArrangement = Arrangement.Center) {
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = onFetchCurrentWeather) {
                Text(text = "Fetch Current Weather")
            }

            Button(onClick = onFetchForecastedWeather) {
                Text(text = "Fetch Forecasted Weather")
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = textState.value ?: "")
    }

}

@Composable
@Preview
fun ContentPreview() {
//    Content(onFetchTapped = { /*TODO*/ }, paddingValues = PaddingValues(4.dp))
}

@Composable
fun ScrollableContent() {

}