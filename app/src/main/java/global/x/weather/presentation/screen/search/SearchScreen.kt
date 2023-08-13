package global.x.weather.presentation.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import global.x.weather.R
import global.x.weather.domain.models.SearchResultModel
import global.x.weather.presentation.framework.components.CenterContentTopAppBar
import global.x.weather.presentation.framework.components.SmallVerticalSpacer
import global.x.weather.presentation.framework.components.TinyVerticalSpacer

@Composable
fun SearchScreen(
    searchFieldValue: State<String?>,
    onSearchFieldValueChanged: (newValue: String) -> Unit,
    recommendationResult: State<List<SearchResultModel>?>,
    onRecommendationClicked: (location: SearchResultModel) -> Unit,
    paddingValues: PaddingValues,
    onSearchFieldValueCleared: () -> Unit
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        CenterContentTopAppBar(
            title = { Text("Search") },
            startIcon = ImageVector.vectorResource(id = R.drawable.ic_back),
            onStartIconClicked = { },
        )
        SmallVerticalSpacer()
        Content(
            searchFieldValue = searchFieldValue,
            onSearchFieldValueChanged = onSearchFieldValueChanged,
            recommendationResult = recommendationResult,
            onRecommendationClicked = onRecommendationClicked,
            onSearchFieldValueCleared = onSearchFieldValueCleared
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    searchFieldValue: State<String?>,
    onSearchFieldValueChanged: (newValue: String) -> Unit,
    recommendationResult: State<List<SearchResultModel>?>,
    onRecommendationClicked: (location: SearchResultModel) -> Unit,
    onSearchFieldValueCleared: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchFieldValue.value ?: "",
            onValueChange = onSearchFieldValueChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Eg. Kathmandu, Nepal")
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onSearchFieldValueCleared) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                        contentDescription = stringResource(
                            id = R.string.content_description_none
                        ),

                        )
                }

            }
        )
        TinyVerticalSpacer()
        recommendationResult.value?.let { list ->
            list.forEach {
                SimpleSearchRecommendation(
                    recommendation = it,
                    onRecommendationClicked = onRecommendationClicked
                )
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchRecommendation(
    recommendation: SearchResultModel,
    onRecommendationClicked: (location: SearchResultModel) -> Unit
) {
    Surface(
        onClick = { onRecommendationClicked(recommendation) },
        modifier = Modifier.clickable(true, onClick = { onRecommendationClicked(recommendation) })
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.height(48.dp)) {
            Text(
                text = recommendation.getDisplayName(), modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

    }

}

@Composable
@Preview
private fun SimpleSearchRecommendationPreview() {
//    SimpleSearchRecommendation(recommendation = "Test", onRecommendationClicked = { })
}

@Composable
@Preview(
    showBackground = true,
)
private fun SearchScreenPreview() {
    val s = remember {
        mutableStateOf("")
    }
    val recommendationResult = remember {
        mutableStateOf(emptyList<SearchResultModel>())
    }
    Content(
        searchFieldValue = s,
        onSearchFieldValueChanged = fun(newValue) {
            s.value = newValue
            recommendationResult.value =
                listOf(SearchResultModel(id = 2, name = "Japan", region = "SEA", country = "Japan"))
        },
        recommendationResult = recommendationResult,
        onRecommendationClicked = { },
        onSearchFieldValueCleared = {
            s.value = ""
            recommendationResult.value = mutableListOf()
        }
    )
}