package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.*
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.network.model.Resource
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private var pageNo = 0

    private var _getData = MutableLiveData<String>()

    private var _getCollectionData = MutableLiveData<String>()

    private var _updatedList = ArrayList<EntityPhoto>()

    private var _setWallpaper = MutableLiveData<EntityPhoto>()

    val getData = _getData.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val photoList = repository.getPhotoList(id, pageNo)
            _updatedList.addAll(photoList)
            emit(Resource.success(data = _updatedList))
        }
    }

    val getCollectionData = _getCollectionData.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.success(data = repository.getCollectionList(id, pageNo)))
        }
    }

    var setWallpaper = Transformations.switchMap(_setWallpaper) {
        repository.setWallpaper(it)
    }

    fun getPhotoList(clientId: String) {
        pageNo++
        _getData.postValue(clientId)
    }

    fun getCollections(clientId: String) {
        _getCollectionData.postValue(clientId)
    }

    fun setWallpaper(data: EntityPhoto) {
        _setWallpaper.value = data
    }
}