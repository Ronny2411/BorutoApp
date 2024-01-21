package com.example.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.example.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val desc: String
) {
    object First: OnBoardingPage(
        image=R.drawable.greetings,
        title = "Greetings",
        desc = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )
    object Second: OnBoardingPage(
        image=R.drawable.explore,
        title = "Explore",
        desc = "Find you favorite heroes and learn of the things that you didn't know about."
    )
    object Third: OnBoardingPage(
        image=R.drawable.power,
        title = "Power",
        desc = "Check out your hero's power and see how much are they strong comparing to others."
    )
}