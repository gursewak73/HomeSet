package com.wallpaper.homeset.network

import com.wallpaper.homeset.api.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val BASE_URL: String = "https://api.unsplash.com"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var apiService : APIService = getRetrofit().create(APIService::class.java)
}