package com.example.borutoapp.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.borutoapp.ui.theme.DarkGrey

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeTopBar {

        }},
        backgroundColor = if (isSystemInDarkTheme()) DarkGrey else Color.White
    ) {
        Modifier.padding(it)
    }
}