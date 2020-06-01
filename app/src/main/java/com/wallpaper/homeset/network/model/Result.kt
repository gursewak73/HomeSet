package com.wallpaper.homeset.network.model

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val messsage: String) : Result<Nothing>()
}