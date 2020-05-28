package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.*
import com.wallpaper.homeset.network.model.Resource
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private var pageNo = 0

    private var _getData = MutableLiveData<String>()

    val getData = _getData.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.success(data = repository.getPhotoList(id, pageNo)))
        }
    }

    fun getPhotoList(clientId: String) {
        pageNo++
        _getData.postValue(clientId)
    }
}