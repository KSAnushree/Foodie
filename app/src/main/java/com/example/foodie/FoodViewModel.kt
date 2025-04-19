package com.example.foodie

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {  //Create a smart assistant to manage food data
    var foodList by mutableStateOf<List<FoodItem>>(emptyList()) //A list that updates the screen when it changes
    var errorMessage by mutableStateOf("") //A string to show errors if needed

    init { //Get the food list right away when ViewModel starts
        fetchFoods()
    }
fun fetchFoods(){
    viewModelScope.launch {
       try {
           foodList= RetrofitClient.apiService.getFood()
           Log.d("FoodViewModel","Fetched ${foodList.size}items:")
           foodList.forEach {
               Log.d("FoodViewModel", "Food: ${it.name} - ${it.description}")

           }
       } catch (e:Exception){
       errorMessage = e.message ?: "Unknown error"
        Log.d("FoodViewModel", errorMessage)
       }
    }
}
}
