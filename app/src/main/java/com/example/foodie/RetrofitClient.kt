package com.example.foodie

import retrofit2.Retrofit
//to use the Retrofit library in this file

import retrofit2.converter.gson.GsonConverterFactory
//to use GsonConverterFactory, which helps Retrofit understand JSON data

object RetrofitClient {

    private const val BASE_URL="http://10.0.2.2:3000/"

    val apiService:FoodApiService by lazy{
         Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(FoodApiService::class.java)
    }
}