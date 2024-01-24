package com.example.borutoapp.presentation.screens.search

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.common.EmptyScreen
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.presentation.common.handlePagingResult
import com.example.borutoapp.ui.theme.DarkGrey
import com.example.borutoapp.ui.theme.SEARCH_TOP_BAR_HEIGHT
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController) {

    val focusManager = LocalFocusManager.current

    var searchQuery by searchViewModel.searchQuery

    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(text = searchQuery,
                onTextChanged = {
                    searchViewModel.updateSearchQuery(it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(it)
                    focusManager.clearFocus()
                },
                onCloseClicked = {
                    navController.popBackStack()
                })
        },
        backgroundColor = if (isSystemInDarkTheme()) DarkGrey else Color.White
    ) {
        Column {
            Modifier.padding(it)
            if (searchQuery == "") {
                searchViewModel.searchHeroes(searchQuery)
                EmptyScreen()
            } else {
                ListContent(
                    heroes = heroes,
                    navController = navController
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPrev() {
    SearchScreen(navController = rememberNavController())
}