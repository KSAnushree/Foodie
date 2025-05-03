package com.example.foodie

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

//to use all the Retrofit HTTP request tools in this file.
//import retrofit2.http.*

//This brings in the Response class, which helps handle the result you get from the server.
//import retrofit2.Response

interface FoodApiService{
     @GET("/foods")
     suspend fun getFood(): List<FoodItem>
     //to make a GET request to the /foods endpoint on the server, and bring back a list of food items.”

    @POST("/foods")
    suspend fun createFood(@Body food: FoodItem): FoodItem
    //Send a new food item to the server, and the server will return the created food item.

    @PUT("/foods/{id}")
    suspend fun updateFood(@Path("id") id: Int, @Body food: FoodItem): FoodItem
    //to update an existing food item in your app’s server/database

    @DELETE("/foods/{id}")
    suspend fun deleteFood(@Path("id") id: Int): Response<Unit>
    //to  delete the food item with specific id



}