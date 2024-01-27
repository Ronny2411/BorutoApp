package com.example.borutoapp.presentation.screens.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.borutoapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.components.InfoBox
import com.example.borutoapp.presentation.components.OrderedList
import com.example.borutoapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINES
import com.example.borutoapp.util.Constants.BASE_URL
import com.example.borutoapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()) {

    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL, label = ""
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(selectedHero = it)
            }
        }) {
        selectedHero?.let {hero->
            BackgroundContent(heroImage = hero.image,
                imageFraction = currentSheetFraction+MIN_BACKGROUND_IMAGE_HEIGHT) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor,
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f))
            Text(text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(8f))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(icon = painterResource(id = R.drawable.bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor)
            InfoBox(icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor)
            InfoBox(icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor)
        }
        Text(
            text = stringResource(R.string.about),
            color = contentColor,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SMALL_PADDING)
        )
        Text(text = selectedHero.about,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor)
            OrderedList(title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor)
            OrderedList(title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor)
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked:()->Unit,
) {
    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = R.drawable.placeholder),
        placeholder = painterResource(id = R.drawable.placeholder)
        )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        Image(painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction)
                .align(Alignment.TopStart))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close),
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .size(INFO_ICON_SIZE)
                    .clickable {
                        onCloseClicked()
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed &&
                    targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded &&
                    targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed &&
                    targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded &&
                    targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Preview
@Composable
fun BottomSheetContentPrev() {
    BottomSheetContent(selectedHero =
    Hero(
        id = 1,
        name = "Sasuke",
        about = "Some random text...",
        image = "",
        rating = 4.5,
        power = 92,
        month = "Oct",
        day = "1st",
        family = listOf("","",""),
        abilities = listOf("","",""),
        natureTypes = listOf("","",""))
    )
}