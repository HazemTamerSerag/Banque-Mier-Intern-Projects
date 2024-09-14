package com.example.cookit.api

import com.example.cookit.constants.Constants
import com.example.cookit.model.CategoryRoot
import com.example.cookit.model.MealRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPICallable {

    @GET(Constants.CATEGORIES_ENDPOINT)
    suspend fun getCategories(): CategoryRoot

    @GET(Constants.FILTER_ENDPOINT)
    suspend fun getMeals(@Query(Constants.CATEGORY_QUERY) category: String): MealRoot


//@GET("/api/json/v1/1/lookup.php?i={idMeal}")
//fun getRecipe(@Query("i") id: String): Call<MealRoot>

    @GET(Constants.LOOKUP_ENDPOINT)
    suspend fun getRecipe(@Query(Constants.MEAL_ID) id: String): MealRoot
}