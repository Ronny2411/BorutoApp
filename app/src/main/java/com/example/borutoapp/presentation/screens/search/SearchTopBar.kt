package com.example.borutoapp.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.SEARCH_TOP_BAR_HEIGHT
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.borutoapp.ui.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChanged:(String)->Unit,
    onSearchClicked:(String)->Unit,
    onCloseClicked:()->Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(SEARCH_TOP_BAR_HEIGHT),
        color = MaterialTheme.colors.topAppBarBackgroundColor,
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        TextField(value = text,
            onValueChange = {onTextChanged(it)},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search here...",
                    color = Color.White,
                    modifier = Modifier.alpha(ContentAlpha.medium))
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor
            ),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()){
                            onTextChanged("")
                        } else {
                            onCloseClicked()
                        }
                    },
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchTopBarPrev() {
    SearchTopBar(text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onCloseClicked = {})
}