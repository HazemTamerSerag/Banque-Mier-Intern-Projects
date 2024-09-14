package com.example.contactapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Human(
    val name: String,
    val phone: String,
    @DrawableRes val picture: Int
)