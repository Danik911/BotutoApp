package com.example.borutoapp.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.R
import com.example.borutoapp.presentation.components.InfoBox
import com.example.borutoapp.presentation.components.OrderedList
import com.example.borutoapp.ui.theme.*
import com.example.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINES
import com.example.borutoapp.util.Constants.BASE_URL
import com.example.borutoapp.util.Constants.MIN_HEIGHT_FRACTION_VALUE
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String, String>
) {

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )


    val imageFraction = bottomSheetState.bottomSheetFraction

    val cornerShape by animateDpAsState(
        targetValue = if (imageFraction == 1f) PADDING_EXTRA_LARGE
        else 0.dp
    )
    var vibrantColor by remember {
        mutableStateOf("#000000")
    }
    var vibrantDarkColor by remember {
        mutableStateOf("#ffffff")
    }
    var vibrantOnDarkColor by remember {
        mutableStateOf("#000000")
    }
    LaunchedEffect(key1 = selectedHero) {
        vibrantColor = colors["vibrant"]!!
        vibrantDarkColor = colors["darkVibrant"]!!
        vibrantOnDarkColor = colors["onDarkVibrant"]!!

    }
    val systemUiController = rememberSystemUiController()
    val topAppBarBackgroundColor = Color(parseColor(vibrantDarkColor))
    SideEffect {
        systemUiController.setStatusBarColor(color = topAppBarBackgroundColor)
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = cornerShape, topEnd = cornerShape),
        sheetContent = {
            if (selectedHero != null) {
                BottomSheetContent(
                    selectedHero = selectedHero,
                    iconColor = Color(parseColor(vibrantColor)),
                    contentColor = Color(parseColor(vibrantOnDarkColor)),
                    sheetBackgroundColor = Color(parseColor(vibrantDarkColor))
                )
            }
        },
        content = {
            if (selectedHero != null) {
                BackgroundContent(
                    imageString = selectedHero.image,
                    imageFraction = imageFraction,
                    backgroundColor = Color(parseColor(vibrantDarkColor))
                ) {
                    navHostController.popBackStack()
                }
            }
        },
        sheetPeekHeight = BOTTOM_SHEET_MIN_HEIGHT
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    iconColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.titleColor,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = PADDING_LARGE)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = PADDING_LARGE),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_BOX_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(
                    id = R.string.logo_icon
                ),
                tint = iconColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.h4.fontSize,
                color = contentColor
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = PADDING_MEDIUM),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor,
                icon = painterResource(
                    id = R.drawable.ic_bolt
                ),
                iconColor = iconColor
            )
            InfoBox(
                bigText = selectedHero.month,
                smallText = "Month",
                textColor = contentColor,
                icon = painterResource(
                    id = R.drawable.ic_calendar
                ),
                iconColor = iconColor
            )
            InfoBox(
                bigText = selectedHero.day,
                smallText = "Birthday",
                textColor = contentColor,
                icon = painterResource(
                    id = R.drawable.ic_cake
                ),
                iconColor = iconColor
            )
        }
        Text(
            modifier = Modifier.padding(bottom = PADDING_SMALL),
            text = "About",
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
        Text(
            modifier = Modifier
                .padding(bottom = PADDING_MEDIUM)
                .alpha(ContentAlpha.medium),
            text = selectedHero.about,
            fontSize = MaterialTheme.typography.body1.fontSize,
            color = contentColor,
            maxLines = ABOUT_TEXT_MAX_LINES
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = "Family",
                textColor = contentColor,
                infoList = selectedHero.family
            )
            OrderedList(
                title = "Abilities",
                textColor = contentColor,
                infoList = selectedHero.abilities
            )
            OrderedList(
                title = "Nature Types",
                textColor = contentColor,
                infoList = selectedHero.natureTypes
            )
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    imageString: String,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${imageString}"
    val painter = rememberImagePainter(data = imageUrl)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_HEIGHT_FRACTION_VALUE)
                .align(alignment = Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop,

            )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .padding(all = PADDING_SMALL)
                    .size(INFO_BOX_ICON_SIZE),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.ic_close),
                    tint = Color.White
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.bottomSheetFraction: Float
    get() {
        val bottomSheerFraction = bottomSheetState.progress.fraction
        val bottomSheetTargetValue = bottomSheetState.targetValue
        val bottomSheetCurrentValue = bottomSheetState.currentValue

        return when {
            bottomSheetTargetValue == BottomSheetValue.Collapsed && bottomSheetCurrentValue == BottomSheetValue.Collapsed -> 1f
            bottomSheetTargetValue == BottomSheetValue.Expanded && bottomSheetCurrentValue == BottomSheetValue.Expanded -> 0f
            bottomSheetTargetValue == BottomSheetValue.Collapsed && bottomSheetCurrentValue == BottomSheetValue.Expanded -> 0f + bottomSheerFraction
            bottomSheetTargetValue == BottomSheetValue.Expanded && bottomSheetCurrentValue == BottomSheetValue.Collapsed -> 1f - bottomSheerFraction
            else -> bottomSheerFraction
        }
    }

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 11,
            name = "Momoshiki",
            image = "/images/momoshiki.jpg",
            about = "Momoshiki Ōtsutsuki (大筒木モモシキ, Ōtsutsuki Momoshiki) was a member of the Ōtsutsuki clan's main family, sent to investigate the whereabouts of Kaguya and her God Tree and then attempting to cultivate a new one out of the chakra of the Seventh Hokage. In the process of being killed by Boruto Uzumaki, Momoshiki placed a Kāma on him, allowing his spirit to remain intact through the mark.",
            rating = 3.9,
            power = 98,
            month = "Jan",
            day = "1st",
            family = listOf(
                "Otsutsuki Clan"
            ),
            abilities = listOf(
                "Rinnegan",
                "Byakugan",
                "Strength"
            ),
            natureTypes = listOf(
                "Fire",
                "Lightning",
                "Wind",
                "Water",
                "Earth"
            )
        )
    )
}