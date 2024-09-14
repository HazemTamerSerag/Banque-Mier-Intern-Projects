package com.example.infinitydogs

import retrofit2.Call
import retrofit2.http.GET

interface DogAPICallable {

    @GET("/api/breeds/image/random")
    fun getRandomDog(): Call<Dog>

}