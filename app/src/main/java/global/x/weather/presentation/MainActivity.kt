package global.x.weather.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import global.x.weather.presentation.framework.theme.XWeatherTheme
import global.x.weather.presentation.screen.home.HomeScreen
import global.x.weather.presentation.screen.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        fun newIntent(launchContext: Context): Intent {
            return Intent(launchContext, MainActivity::class.java)
        }
    }

    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        Content(paddingValues = it)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Content(paddingValues: PaddingValues) {
        Scaffold(modifier = Modifier.padding(paddingValues)) {
            HomeScreen(
                onFetchCurrentWeather = homeViewModel::onFetchCurrentWeatherData,
                onFetchForecastedWeather = homeViewModel::onFetchForecastedWeatherData,
                paddingValues = it,
                textState = homeViewModel.testString.observeAsState()
            )
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        XWeatherTheme {
            Greeting("Android")
        }
    }
}