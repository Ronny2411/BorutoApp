package com.example.borutoapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import com.example.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.example.borutoapp.ui.theme.RATING_PLACEHOLDER_SIZE
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.ShimmerDarkGrey
import com.example.borutoapp.ui.theme.ShimmerLightGrey
import com.example.borutoapp.ui.theme.ShimmerMediumGrey

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(count = 2){
            AnimateSimmerItem()
        }
    }
}

@Composable
fun AnimateSimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 750,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .fillMaxWidth(),
        color = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGrey,
        shape = RoundedCornerShape(LARGE_PADDING)
    ) {
        Column(modifier = Modifier
            .padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom) {
            Surface(modifier = Modifier
                .alpha(alpha)
                .fillMaxWidth(0.5f)
                .height(NAME_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) ShimmerDarkGrey else ShimmerMediumGrey,
                shape = RoundedCornerShape(MEDIUM_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            repeat(3){
                Surface(modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth()
                    .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGrey else ShimmerMediumGrey,
                    shape = RoundedCornerShape(MEDIUM_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5){
                    Surface(modifier = Modifier
                        .alpha(alpha)
                        .size(RATING_PLACEHOLDER_SIZE),
                        color = if (isSystemInDarkTheme()) ShimmerDarkGrey else ShimmerMediumGrey,
                        shape = RoundedCornerShape(MEDIUM_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItemPrev() {
    AnimateSimmerItem()
}