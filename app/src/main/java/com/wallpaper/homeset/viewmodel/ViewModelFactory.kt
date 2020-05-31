package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.repository.MainRepository
import com.wallpaper.homeset.service.FeatureService

class ViewModelFactory(private var apiHelper: APIHelper, private var featureService : FeatureService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper, featureService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}