package com.example.borutoapp.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.ui.theme.DarkGrey
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { HomeTopBar {

        }},
        backgroundColor = if (isSystemInDarkTheme()) DarkGrey else Color.White
    ) {
        Modifier.padding(it)
        RatingBar(value = 4.6f,
            style = RatingBarStyle.Default,
            onValueChange = {},
            onRatingChanged = {})
    }
}
