package com.lalosapps.retrofitviews.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("random")
    suspend fun getRandomChuckJoke(): Response<ChuckJoke>
}