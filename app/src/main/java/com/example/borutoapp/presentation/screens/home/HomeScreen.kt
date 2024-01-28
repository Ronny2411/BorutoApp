package com.example.borutoapp.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.ui.theme.DarkGrey
import com.example.borutoapp.ui.theme.Purple40
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
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
    Scaffold(
        topBar = { HomeTopBar {
            navController.navigate(Screen.Search.route)
        }},
        backgroundColor = if (isSystemInDarkTheme()) DarkGrey else Color.White
    ) {
        Modifier.padding(it)
        ListContent(
            heroes = allHeroes,
            navController = navController
        )
    }
}
