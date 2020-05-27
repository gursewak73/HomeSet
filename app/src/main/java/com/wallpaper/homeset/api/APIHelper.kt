package com.wallpaper.homeset.api

class APIHelper(var apiService: APIService) {

    suspend fun getCollections() = apiService.getCollections()

}