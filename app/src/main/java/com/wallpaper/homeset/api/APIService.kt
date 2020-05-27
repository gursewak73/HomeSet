package com.wallpaper.homeset.api

import retrofit2.http.GET

interface APIService {

    @GET("f")
    suspend fun getCollections()
}