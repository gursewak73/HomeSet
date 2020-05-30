package com.wallpaper.homeset.callback

import com.downloader.Error

interface DownloadCallback {

    fun onDownloadComplete()
    fun onError()
    fun setProgress(progress : Int)

}