package com.wallpaper.homeset.repository

import androidx.lifecycle.MutableLiveData
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.callback.OnActionListener
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.network.model.Resource
import com.wallpaper.homeset.network.model.Status
import com.wallpaper.homeset.service.FeatureService

class MainRepository(private var apiHelper: APIHelper) {

    var featureService = FeatureService()

    suspend fun getPhotoList(clientId: String, pageNo: Int) = apiHelper.getPhotoList(clientId, pageNo)

    fun setWallpaper(data: EntityPhoto) : MutableLiveData<FeatureModel> {
        val liveData = MutableLiveData<FeatureModel>()
        featureService.setWallpaper(data, object : OnActionListener {
            override fun showProgressDialog(show: Boolean) {
                liveData.postValue(FeatureModel().setProgress(show))
            }

            override fun setProgressMsg(msg: String) {
            }

            override fun showToast(msg: String) {
                liveData.postValue(FeatureModel().setToast(msg))
            }

        })
        return liveData
    }
}