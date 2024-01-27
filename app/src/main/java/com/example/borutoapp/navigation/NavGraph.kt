package com.example.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.screens.details.DetailsScreen
import com.example.borutoapp.presentation.screens.home.HomeScreen
import com.example.borutoapp.presentation.screens.search.SearchScreen
import com.example.borutoapp.presentation.screens.splash.SplashScreen
import com.example.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.example.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Screen.Splash.route){
        composable(Screen.Splash.route){
            SplashScreen(navController)
        }
        composable(Screen.Welcome.route){
            WelcomeScreen(navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController)
        }
        composable(Screen.Details.route,
            listOf(navArgument(DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ){
            DetailsScreen(navController = navController)
        }
        composable(Screen.Search.route){
            SearchScreen(navController = navController)
        }
    }
}