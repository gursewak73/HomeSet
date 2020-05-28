package com.wallpaper.homeset.api

import com.wallpaper.homeset.entity.EntityPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.ArrayList

interface APIService {

    @GET("f")
    suspend fun getCollections()

    @GET("photos/")
    suspend fun getPhotoList(@Query("client_id") clientId: String, @Query("page") pageNo: Int): ArrayList<EntityPhoto>

    @GET("collections/")
    suspend fun getCollections(@Query("client_id") clientId: String, @Query("page") pageNo: Int, @Query("per_page") count: Int): ArrayList<EntityPhoto>

    @GET("collections/{id}/photos")
    suspend fun getCollectionPhotoList(@Path("id") id: String, @Query("client_id") clientId: String, @Query("page") pageNo: Int, @Query("per_page") count: Int): ArrayList<EntityPhoto>
}