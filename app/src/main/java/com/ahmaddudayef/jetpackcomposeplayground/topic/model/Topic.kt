package com.ahmaddudayef.jetpackcomposeplayground.topic.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val availableCourse: Int,
    @DrawableRes val imageRes: Int
)
