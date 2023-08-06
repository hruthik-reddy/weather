package global.x.weather.presentation.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    Scaffold(
        modifier = Modifier.padding(paddingValues)
    ) {
        Screen(paddingValues = it)
    }
}

@Composable
fun Screen(paddingValues: PaddingValues) {

}

@Composable
fun Content(paddingValues: PaddingValues) {


}

@Composable
fun ScrollableContent(){

}