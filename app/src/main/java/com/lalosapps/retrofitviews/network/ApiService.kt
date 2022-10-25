package com.lalosapps.retrofitviews.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    private const val BASE_URL = "https://api.chucknorris.io/jokes/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: ApiClient by lazy {
        retrofit.create()
    }
}