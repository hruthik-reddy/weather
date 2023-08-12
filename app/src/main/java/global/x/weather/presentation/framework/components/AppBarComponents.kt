package global.x.weather.presentation.framework.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import global.x.weather.R


@Composable
fun BlankTopAppBar() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.navigation_bar_height)))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterContentTopAppBar(
    title: @Composable () -> Unit,
    startIcon: ImageVector,
    startIconContentDescription: String,
    onStartIconClicked: () -> Unit,
    endIcon: ImageVector,
    endIconContentDescription: String,
    onEndIconClicked: () -> Unit,

    ) {
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = onStartIconClicked) {
                Icon(imageVector = startIcon, contentDescription = startIconContentDescription)
            }
        },
        actions = {
            IconButton(onClick = onEndIconClicked, content = {
                Icon(imageVector = endIcon, contentDescription = endIconContentDescription)
            })
        }
    )
}

@Composable
@Preview
private fun CenterContentTopAppBarPreview() {
    CenterContentTopAppBar(
        title = { Text("Pokhara") },
        startIcon = ImageVector.vectorResource(id = R.drawable.ic_map),
        startIconContentDescription = stringResource(id = R.string.content_description_saved_regions),
        onStartIconClicked = { /*TODO*/ },
        endIcon = ImageVector.vectorResource(id = R.drawable.ic_search),
        endIconContentDescription = stringResource(id = R.string.content_description_search_regions)
    ) {

    }
}


@Composable
fun AppBarNavigationBackButton(
    onClick: () -> Unit,
) {
    IconButton(
        content = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Navigate back"
            )
        },
        onClick = onClick
    )
}