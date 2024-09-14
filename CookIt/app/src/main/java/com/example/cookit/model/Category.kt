package com.example.cookit.model

import com.example.cookit.constants.Constants
import com.google.gson.annotations.SerializedName

data class CategoryRoot (val categories: List<Category>)

data class Category (
    @SerializedName(Constants.CATEGORY_NAME)
    val name: String,
    @SerializedName(Constants.CATEGORY_IMAGE)
    val image: String,

)