package com.wallpaper.homeset.repository

import com.wallpaper.homeset.api.APIHelper

class MainRepository(private var apiHelper: APIHelper) {

    suspend fun getPhotoList(clientId: String, pageNo: Int) = apiHelper.getPhotoList(clientId, pageNo)
}