package global.x.weather.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import global.x.weather.domain.models.SearchResultModel
import global.x.weather.infrastructure.util.observeAsAction
import global.x.weather.presentation.framework.theme.XWeatherTheme
import global.x.weather.presentation.screen.WeatherNavigationRoute
import global.x.weather.presentation.screen.favorites.FavoriteScreen
import global.x.weather.presentation.screen.favorites.FavoriteViewModel
import global.x.weather.presentation.screen.home.HomeScreen
import global.x.weather.presentation.screen.home.HomeViewModel
import global.x.weather.presentation.screen.search.SearchScreen
import global.x.weather.presentation.screen.search.SearchViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        fun newIntent(launchContext: Context): Intent {
            return Intent(launchContext, MainActivity::class.java)
        }
    }

    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XWeatherTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = WeatherNavigationRoute.Home.getName()
                ) {
                    composable(
                        WeatherNavigationRoute.Home.getName(),
                        WeatherNavigationRoute.Home.args
                    ) {
                        Scaffold() {
                            HomeScreenContent(
                                paddingValues = it,
                                onSearchIconTapped = mainViewModel::onNavigateToSearch,
                                onLocationIconTapped = mainViewModel::onNavigateToFavorites
                            )
                        }
                    }
                    composable(WeatherNavigationRoute.Search.getName()) {
                        Scaffold() {
                            SearchScreenContent(
                                paddingValues = it,
                                onBackIconTapped = mainViewModel::onNavigateBack,
                                searchStringState = searchViewModel.searchString.observeAsState(),
                                onSearchStringChanged = searchViewModel::onSearchStringChanged,
                                isClearButtonVisible = searchViewModel.isClearSearchIconVisible.observeAsState(),
                                onRecommendationItemTapped = { searchResultModel ->
                                    mainViewModel.onShowWeatherDetail(searchResultModel.toFavoriteLocationModel())
                                },
                                onSearchFieldValueCleared = searchViewModel::onSearchFieldValueCleared,
                                recommendationResultState = searchViewModel.autocompleteResult.observeAsState()

                            )
                        }
                    }
                    composable(WeatherNavigationRoute.Favorite.getName()) {
                        val viewModel = hiltViewModel<FavoriteViewModel>()
                        Scaffold() {
                            FavoriteScreen(
                                paddingValues = it,
                                onBackIconClicked = mainViewModel::onNavigateBack,
                                dateState = viewModel.dateState.observeAsState(),
                                favoriteLocationDataList = viewModel.favoriteLocationDataList.observeAsState(),
                                onFavoriteItemTapped = viewModel::onFavoriteItemTapped
                            )

                        }
                    }
                    registerObservers(navController = navController)

                }

            }
        }
    }

    private fun registerObservers(navController: NavController) {
        mainViewModel.onShowWeatherDetail.observeAsAction(
            lifecycleOwner = this,
            action = {

                navController.navigate("${WeatherNavigationRoute.Home.getName()}/${it.toStringArg()}")
            },
            onActionResolved = mainViewModel::onShowWeatherDetailResolved
        )
        mainViewModel.onNavigateToSearch.observeAsAction(
            lifecycleOwner = this,
            action = {
                navController.navigate(WeatherNavigationRoute.Search.getName())
            },
            onActionResolved = mainViewModel::onNavigateToSearchResolved
        )
        mainViewModel.onNavigateToFavorites.observeAsAction(
            lifecycleOwner = this,
            action = {
                navController.navigate(
                    WeatherNavigationRoute.Favorite.getName()
                )
            },
            onActionResolved = mainViewModel::onNavigateToFavoritesResolved
        )
        mainViewModel.onNavigateBack.observeAsAction(
            lifecycleOwner = this,
            action = {
                navController.popBackStack()
            },
            onActionResolved = mainViewModel::onNavigateBackResolved
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FavoriteLocationContent(paddingValues: PaddingValues) {
        Scaffold(modifier = Modifier.padding(paddingValues)) {
            FavoriteScreen(
                paddingValues = it,
                onBackIconClicked = {},
                dateState = favoriteViewModel.dateState.observeAsState(),
                favoriteLocationDataList = favoriteViewModel.favoriteLocationDataList.observeAsState(),
                onFavoriteItemTapped = favoriteViewModel::onFavoriteItemTapped
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun HomeScreenContent(
        paddingValues: PaddingValues,
        onSearchIconTapped: () -> Unit,
        onLocationIconTapped: () -> Unit
    ) {

        Scaffold(modifier = Modifier.padding(paddingValues)) { it ->
            HomeScreen(
                weatherDataState = homeViewModel.forecastedWeatherData.observeAsState(),
                paddingValues = it,
                onSearchIconTapped = onSearchIconTapped,
                onLocationIconTapped = onLocationIconTapped
            )

        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchScreenContent(
        paddingValues: PaddingValues,
        onBackIconTapped: () -> Unit,
        searchStringState: State<String?>,
        onSearchStringChanged: (String) -> Unit,
        recommendationResultState: State<List<SearchResultModel>?>,
        onRecommendationItemTapped: (SearchResultModel) -> Unit,
        onSearchFieldValueCleared: () -> Unit,
        isClearButtonVisible: State<Boolean?>
    ) {
        Scaffold(modifier = Modifier.padding(paddingValues)) { paddingValues ->
            SearchScreen(
                searchFieldValue = searchStringState,
                onSearchFieldValueChanged = onSearchStringChanged,
                recommendationResult = recommendationResultState,
                onRecommendationClicked = onRecommendationItemTapped,
                paddingValues = paddingValues,
                onSearchFieldValueCleared = onSearchFieldValueCleared,
                isClearSearchQueryVisible = isClearButtonVisible,
                onBackIconTapped = onBackIconTapped
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