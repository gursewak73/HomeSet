package com.wallpaper.homeset.api

class APIHelper(var apiService: APIService) {

    suspend fun getCollections() = apiService.getCollections()

    suspend fun getPhotoList(clientId: String, pageNo: Int) =
        apiService.getPhotoList(clientId, pageNo)

    suspend fun getCollections(clientId: String, pageNo: Int, count: Int) =
        apiService.getCollections(clientId, pageNo, count)

    suspend fun getCollectionPhotoList(id: String, clientId: String, pageNo: Int, count: Int) =
        apiService.getCollectionPhotoList(id, clientId, pageNo, count)

}