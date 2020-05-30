package com.wallpaper.homeset.callback

interface DownloadCallback {

    fun onDownloadComplete()
    fun onError()
    fun setProgress(progress : Int)

}