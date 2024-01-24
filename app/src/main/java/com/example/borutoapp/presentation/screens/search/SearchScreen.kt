package com.example.borutoapp.presentation.screens.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SearchTopBar(text = "",
                onTextChanged = {},
                onSearchClicked = {},
                onCloseClicked = {
                    navController.popBackStack()
                })
        }
    ) {
        Modifier.padding(it)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPrev() {
    SearchScreen(navController = rememberNavController())
}