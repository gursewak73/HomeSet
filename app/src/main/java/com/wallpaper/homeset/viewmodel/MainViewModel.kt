package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.*
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.network.model.Resource
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private var pageNo = 0

    private var _getData = MutableLiveData<String>()

    private var _updatedList = ArrayList<EntityPhoto>()

    val getData = _getData.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val photoList = repository.getPhotoList(id, pageNo)
            _updatedList.addAll(photoList)
            emit(Resource.success(data = _updatedList))
        }
    }

    fun getPhotoList(clientId: String) {
        pageNo++
        _getData.postValue(clientId)
    }
}