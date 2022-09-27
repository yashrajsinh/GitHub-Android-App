package com.yashrajsinh.gitprofile.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//RETRO FIT CLIENT
object RetrofitClient {
    //GIT HUB URL
    private const val BASE_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //API CLASS
    val apiInstance = retrofit.create(Api::class.java)
}