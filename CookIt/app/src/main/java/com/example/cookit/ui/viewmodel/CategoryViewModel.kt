package com.example.cookit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookit.api.MealAPIServices
import com.example.cookit.model.Category
import com.example.cookit.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _categories.update {
                    MealAPIServices.callable.getCategories().categories
                }
                _hasError.update { false }
            }
            catch (e: Exception){
                Log.d("trace","error ${e.message}}")
                _hasError.update { true }
            }
        }
    }
    fun getMeals(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _meals.update {
                    MealAPIServices.callable.getMeals(category).meals
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