package com.wallpaper.homeset.repository

import androidx.lifecycle.MutableLiveData
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.service.FeatureService

class MainRepository(private var apiHelper: APIHelper) {

    var featureService = FeatureService()

    suspend fun getPhotoList(clientId: String, pageNo: Int) =
        apiHelper.getPhotoList(clientId, pageNo)


    suspend fun getCollectionList(clientId: String, pageNo: Int) =
        apiHelper.getCollections(clientId, pageNo, 10)

    fun setWallpaper(data: EntityPhoto): MutableLiveData<FeatureModel> {
        val liveData = MutableLiveData<FeatureModel>()
        featureService.setWallpaper(data) {
            liveData.postValue(it)
        }
        return liveData
    }

    fun downloadWallpaper(data: EntityPhoto): MutableLiveData<FeatureModel> {
        val liveData = MutableLiveData<FeatureModel>()
        featureService.downloadWallpaper(data) {
            liveData.postValue(it)
        }
        return liveData
    }
}