package com.example.cookit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookit.api.MealAPIServices
import com.example.cookit.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _meals = MutableStateFlow(Meal())
    val meals = _meals.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()


    fun getRecipes(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _meals.update {
                    MealAPIServices.callable.getRecipe(id).meals[0]
                }
                _hasError.update { false }
            }
            catch (e: Exception){
                Log.d("trace","Meals Error ${e.message}}")
                _hasError.update { true }
            }
        }
    }
}