package global.x.weather.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onFetchTapped: () -> Unit, paddingValues: PaddingValues) {
    Scaffold(
        modifier = Modifier.padding(paddingValues)
    ) {
        Screen(onFetchTapped = onFetchTapped, paddingValues = it)
    }
}

@Composable
fun Screen(onFetchTapped: () -> Unit, paddingValues: PaddingValues) {
    Content(onFetchTapped = onFetchTapped, paddingValues = paddingValues)

}

@Composable
fun Content(onFetchTapped: () -> Unit, paddingValues: PaddingValues) {
    Column(verticalArrangement = Arrangement.Center){
        Button(onClick = onFetchTapped) {
            Text(text = "Fetch")
        }
    }

}
@Composable
@Preview
fun ContentPreview(){
    Content(onFetchTapped = { /*TODO*/ }, paddingValues = PaddingValues(4.dp) )
}

@Composable
fun ScrollableContent() {

}