package com.example.borutoapp.presentation.screens.splash

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutoapp.R
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.Pink40
import com.example.borutoapp.ui.theme.Pink80
import com.example.borutoapp.ui.theme.Purple40
import com.example.borutoapp.ui.theme.Purple80
import com.example.borutoapp.ui.theme.PurpleGrey40

@Composable
fun SplashScreen(navController: NavHostController,
                 splashViewModel: SplashViewModel = hiltViewModel()){
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
    val degrees = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 = true){
        degrees.animateTo(360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees.value)
}

@Composable
fun Splash(degrees: Float){
    Box(modifier = Modifier
        .background(
            if (!isSystemInDarkTheme()) Brush.verticalGradient(listOf(Purple40, Pink80))
            else Brush.verticalGradient(listOf(Color.Black, Color.DarkGray))
        )
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier.rotate(degrees))
    }
}

@Preview(showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun SplashScreenPrev() {
    Splash(0f)
}