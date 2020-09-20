package com.wallpaper.homeset.viewmodel

import androidx.lifecycle.*
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.network.model.Result
import com.wallpaper.homeset.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var pageNo = 0

    private var _getData = MutableLiveData<String>()

    private var _getCollectionData = MutableLiveData<String>()

    private var _getCollectionPhotos = MutableLiveData<String>()

    private var _updatedList = ArrayList<EntityPhoto>()

    private var _setWallpaper = MutableLiveData<EntityPhoto>()

    private var _openFullScreen = MutableLiveData<Int>()

    val openFullScreen : LiveData<Int> =_openFullScreen

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
            try {
                val photoList = repository.getPhotoList(pageNo)
                _updatedList.addAll(photoList)
                emit(Result.Success(data = _updatedList))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    val getCollectionData = _getCollectionData.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                val collectionList = repository.getCollectionList(pageNo)
                val allPhoto = EntityPhoto()
                allPhoto.title = "All"
                collectionList.add(0, allPhoto)
                emit(Result.Success(data = collectionList))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    val getCollectionPhotos = _getCollectionPhotos.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Result.Success(data = repository.getCollectionPhotoList(id, pageNo)))
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

    fun openOnFullScreen(position : Int) {
        _openFullScreen.value = position
    }

    private fun getCollections() {
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