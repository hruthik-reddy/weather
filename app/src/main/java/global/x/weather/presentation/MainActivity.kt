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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import global.x.weather.R
import global.x.weather.domain.models.FavoriteLocationModel
import global.x.weather.domain.models.SearchResultModel
import global.x.weather.domain.models.WeatherData
import global.x.weather.infrastructure.util.observeAsAction
import global.x.weather.presentation.framework.theme.XWeatherTheme
import global.x.weather.presentation.screen.WeatherNavigationRoute
import global.x.weather.presentation.screen.favorites.FavoriteScreen
import global.x.weather.presentation.screen.favorites.FavoriteViewModel
import global.x.weather.presentation.screen.home.HomeViewModel
import global.x.weather.presentation.screen.home.WeatherDetailScreen
import global.x.weather.presentation.screen.search.SearchScreen
import global.x.weather.presentation.screen.search.SearchViewModel
import global.x.weather.presentation.screen.weather_detail.WeatherDetailViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        fun newIntent(launchContext: Context): Intent {
            return Intent(launchContext, MainActivity::class.java)
        }
    }

    private val mainViewModel: MainViewModel by viewModels()

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
                        WeatherNavigationRoute.Home.getName()
                    ) {
                        val homeViewModel = hiltViewModel<HomeViewModel>()

                        Scaffold {
                            HomeScreenPage(
                                paddingValues = it,
                                onSearchIconTapped = mainViewModel::onNavigateToSearch,
                                onLocationIconTapped = mainViewModel::onNavigateToFavorites,
                                weatherDataState = homeViewModel.forecastedWeatherData.observeAsState(),

                                )
                        }
                    }
                    composable(WeatherNavigationRoute.Search.getName()) {
                        val searchViewModel = hiltViewModel<SearchViewModel>()
                        Scaffold() {
                            SearchPage(
                                paddingValues = it,
                                onBackIconTapped = mainViewModel::onNavigateBack,
                                searchStringState = searchViewModel.searchString.observeAsState(),
                                onSearchStringChanged = searchViewModel::onSearchStringChanged,
                                isClearButtonVisible = searchViewModel.isClearSearchIconVisible.observeAsState(),
                                onRecommendationItemTapped = { searchResultModel ->
                                    searchViewModel.onSearchRecommendationItemClicked(searchResultModel)
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
                            FavoriteScreenPage(
                                paddingValues = it,
                                onBackIconClicked = mainViewModel::onNavigateBack,
                                dateState = viewModel.dateState.observeAsState(),
                                favoriteLocationDataList = viewModel.favoriteLocationDataList.observeAsState(),
                                onFavoriteItemTapped = { favoriteLocationModel ->
                                    mainViewModel.onShowWeatherDetail(favoriteLocationModel)
                                },)


                        }
                    }
                    composable(
                        route = WeatherNavigationRoute.SearchResult.getName(), arguments =
                        WeatherNavigationRoute.SearchResult.args
                    ) {
                        val viewModel = hiltViewModel<WeatherDetailViewModel>()
                        Scaffold() {
                            SearchDetailPage(
                                paddingValues = it,
                                onNavigateBack = mainViewModel::onNavigateBack,
                                weatherDataState = viewModel.forecastedWeatherData.observeAsState(),
                                onSaveLocationTapped = viewModel::onSaveLocationTapped,
                                appBarEndIcon = viewModel.favoriteIcon.observeAsState()
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
                navController.navigate(WeatherNavigationRoute.SearchResult.getNavigationRequest(it.toStringArg()))
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
    private fun FavoriteScreenPage(
        paddingValues: PaddingValues,
        onBackIconClicked: () -> Unit,
        dateState: State<String?>,
        favoriteLocationDataList: State<List<FavoriteLocationModel>?>,
        onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
    ) {

        FavoriteScreen(
            paddingValues = paddingValues,
            onBackIconClicked = onBackIconClicked,
            dateState = dateState,
            favoriteLocationDataList = favoriteLocationDataList,
            onFavoriteItemTapped = onFavoriteItemTapped,
        )

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun HomeScreenPage(
        paddingValues: PaddingValues,
        onSearchIconTapped: () -> Unit,
        onLocationIconTapped: () -> Unit,
        weatherDataState: State<List<WeatherData.Daily>?>
    ) {

        Scaffold(modifier = Modifier.padding(paddingValues)) { it ->
            WeatherDetailScreen(
                weatherDataState = weatherDataState,
                paddingValues = it,
                onAppBarStartIconTapped = onLocationIconTapped,
                onAppBarEndIconTapped = onSearchIconTapped,
                appbarStartIcon = ImageVector.vectorResource(id = R.drawable.ic_map),
                appbarEndIcon = ImageVector.vectorResource(id = R.drawable.ic_search)
            )

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchDetailPage(
        paddingValues: PaddingValues,
        onNavigateBack: () -> Unit,
        weatherDataState: State<List<WeatherData.Daily>?>,
        onSaveLocationTapped: () -> Unit,
        appBarEndIcon: State<Int?>
    ) {

        Scaffold(modifier = Modifier.padding(paddingValues)) { it ->
            WeatherDetailScreen(
                weatherDataState = weatherDataState,
                paddingValues = it,
                onAppBarStartIconTapped = onNavigateBack,
                appbarStartIcon = ImageVector.vectorResource(id = R.drawable.ic_back),
                onAppBarEndIconTapped = onSaveLocationTapped,
                appbarEndIcon =   ImageVector.vectorResource(id = appBarEndIcon.value ?: R.drawable.ic_heart),
            )

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchPage(
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

}