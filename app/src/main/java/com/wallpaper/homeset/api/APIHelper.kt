package com.wallpaper.homeset.api

import com.wallpaper.homeset.util.Constant
import javax.inject.Inject

class APIHelper @Inject constructor(var apiService: APIService) {

    suspend fun getPhotoList(pageNo: Int) =
        apiService.getPhotoList(Constant.CLIENT_ID, pageNo)

    suspend fun getCollections(pageNo: Int, count: Int) =
        apiService.getCollections(Constant.CLIENT_ID, pageNo, count)

    suspend fun getCollectionPhotoList(id: String, pageNo: Int, count: Int) =
        apiService.getCollectionPhotoList(id, Constant.CLIENT_ID, pageNo, count)

}