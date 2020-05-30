package com.wallpaper.homeset.service

import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.wallpaper.homeset.callback.DownloadCallback

class DownloadService {

    private object Holder {
        val INSTANCE = DownloadService()
    }

    companion object {
        fun getInstance(): DownloadService {
            return Holder.INSTANCE
        }
    }

    fun downloadFile(url: String, absolutePath: String, fileName: String, downloadCallback: DownloadCallback) {
        PRDownloader.download(url, absolutePath, fileName)
                .build()
                .setOnStartOrResumeListener {
                }
                .setOnPauseListener { }
                .setOnCancelListener { }
                .setOnProgressListener { progress ->
//                    val percent = (progress.currentBytes * 100 / progress.totalBytes * 100)
//                    downloadCallback.setProgress(percent.toInt() / 100)
                }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        downloadCallback.onDownloadComplete()
                    }

                    override fun onError(error: Error) {
                        downloadCallback.onError()
                    }
                })
    }
}