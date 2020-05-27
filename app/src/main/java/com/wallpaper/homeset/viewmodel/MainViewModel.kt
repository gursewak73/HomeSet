package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.ViewModel
import com.wallpaper.homeset.model.Resource
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun getCollections() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCollections()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}