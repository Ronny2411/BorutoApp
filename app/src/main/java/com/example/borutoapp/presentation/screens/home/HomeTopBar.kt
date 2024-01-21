package com.example.borutoapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.borutoapp.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(onSearchClicked:()->Unit) {
    TopAppBar(
        title = {Text(text = stringResource(R.string.explore),
            color = MaterialTheme.colors.topAppBarContentColor)} ,
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colors.topAppBarContentColor)
            }
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarPrev() {
    HomeTopBar {

    }
}