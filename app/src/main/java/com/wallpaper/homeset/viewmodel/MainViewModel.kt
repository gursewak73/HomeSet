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

    private var _getCollectionPhotos = MutableLiveData<String>()

    private var _updatedList = ArrayList<EntityPhoto>()

    private var _setWallpaper = MutableLiveData<EntityPhoto>()

    var listType = LIST_TYPE_ALL

    companion object {
        const val LIST_TYPE_ALL = "list_type_all"
        private var LIST_TYPE_COLLECTION = "list_type_collection"
    }

    init {
        getCollections()
        getPhotoList()
    }

    val getData = _getData.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val photoList = repository.getPhotoList(pageNo)
            _updatedList.addAll(photoList)
            emit(Resource.success(data = _updatedList))
        }
    }

    val getCollectionData = _getCollectionData.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val collectionList = repository.getCollectionList(pageNo)
            val allPhoto = EntityPhoto()
            allPhoto.title = "All"
            collectionList.add(0, allPhoto)
            emit(Resource.success(data = collectionList))
        }
    }

    val getCollectionPhotos = _getCollectionPhotos.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.success(data = repository.getCollectionPhotoList(id, pageNo)))
        }
    }

    var setWallpaper = Transformations.switchMap(_setWallpaper) {
        repository.setWallpaper(it)
    }

    fun getPhotoList() {
        listType = LIST_TYPE_ALL
        pageNo++
        _getData.value = ""
    }

    fun getCollections() {
        _getCollectionData.value = ""
    }

    fun getCollectionPhotos(id: String) {
        pageNo = 0
        listType = LIST_TYPE_COLLECTION
        _getCollectionPhotos.value = id
    }

    fun setWallpaper(data: EntityPhoto) {
        _setWallpaper.value = data
    }
}