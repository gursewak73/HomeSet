package com.wallpaper.homeset.network

import com.wallpaper.homeset.api.APIService
import com.wallpaper.homeset.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var apiService : APIService = getRetrofit().create(APIService::class.java)
}