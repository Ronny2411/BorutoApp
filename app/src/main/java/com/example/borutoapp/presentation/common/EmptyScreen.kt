package com.example.borutoapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.DarkGrey
import com.example.borutoapp.ui.theme.LightGrey
import com.example.borutoapp.ui.theme.NETWORK_ERROR_ICON_SIZE
import com.example.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember{
        mutableStateOf(parseErrorMessage(error = error))
    }
    val icon by remember {
        mutableStateOf(R.drawable.network_error)
    }
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ), label = ""
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon),
            modifier = Modifier.size(NETWORK_ERROR_ICON_SIZE).alpha(alphaAnim),
            tint = if (isSystemInDarkTheme()) LightGrey else DarkGrey)
        Text(text = message,
            color = if (isSystemInDarkTheme()) LightGrey else DarkGrey,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = SMALL_PADDING).alpha(alphaAnim))
    }
}

fun parseErrorMessage(error: LoadState.Error): String{
    return when{
        error.error is SocketTimeoutException -> {
            "Server Unavailable"
        }
        error.error is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}