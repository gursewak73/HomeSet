package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.ViewModel
import com.wallpaper.homeset.network.model.Resource
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private var pageNo = 0

    fun getPhotoList(clientId: String) = liveData(Dispatchers.IO) {
        pageNo++
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPhotoList(clientId, pageNo)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}