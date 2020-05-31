package com.wallpaper.homeset.repository

import androidx.lifecycle.MutableLiveData
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.service.FeatureService

class MainRepository(private var apiHelper: APIHelper) {

    var featureService = FeatureService()

    suspend fun getPhotoList(pageNo: Int) =
        apiHelper.getPhotoList(pageNo)


    suspend fun getCollectionList(pageNo: Int) =
        apiHelper.getCollections(pageNo, 10)


    suspend fun getCollectionPhotoList(id: String, pageNo: Int) =
        apiHelper.getCollectionPhotoList(id, pageNo, 10)

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