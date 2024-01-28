package com.example.borutoapp.presentation.screens.welcome

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.INDICATOR_SIZE
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.PAGE_INDICATOR_WIDTH
import com.example.borutoapp.ui.theme.Purple40
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.descColor
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.example.borutoapp.util.Constants.ON_BOARDING_LAST_PAGE
import com.example.borutoapp.util.Constants.ON_BOARDING_PAGE_COUNT

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController,
                  welcomeViewModel: WelcomeViewModel = hiltViewModel()){
    val darkTheme: Boolean = isSystemInDarkTheme()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            if (darkTheme) {
                window.statusBarColor = Color.Black.toArgb()
                window.navigationBarColor = Color.Black.toArgb()
            }
            else{
                window.statusBarColor = Purple40.toArgb()
                window.navigationBarColor = Purple40.toArgb()
            }
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState(
    ) {
        // provide pageCount
        ON_BOARDING_PAGE_COUNT
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)) {
        HorizontalPager(state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(10f)) {position->
            PagerScreen(onBoardingPage = pages[position])
        }
        PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage,
            modifier = Modifier
                .width(PAGE_INDICATOR_WIDTH)
                .align(Alignment.CenterHorizontally)
                .weight(1f))
        FinishButton(pagerState = pagerState,
            modifier = Modifier.weight(1f)) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Image(painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
        )
        Text(text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h4.fontSize,
            textAlign = TextAlign.Center)
        Text(text = onBoardingPage.desc,
            color = MaterialTheme.colors.descColor,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            modifier = Modifier
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            textAlign = TextAlign.Center)
    }
}

@Composable
fun PageIndicator(
    pageSize : Int,
    modifier: Modifier = Modifier,
    selectedPage : Int,
    selectedColor : Color = Purple40,
    unselectedColor: Color = Color.LightGray
){
    Row (modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween){
        repeat(pageSize){page->
            Box(modifier = Modifier
                .size(INDICATOR_SIZE)
                .clip(CircleShape)
                .background(if (page == selectedPage) selectedColor else unselectedColor))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick:()->Unit
){
    Row(modifier.padding(horizontal = EXTRA_LARGE_PADDING),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {
        AnimatedVisibility(visible = pagerState.currentPage == ON_BOARDING_LAST_PAGE,
            modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple40,
                    contentColor = Color.White
                )) {
                Text(text = "Finish")
            }
        }
    }
}