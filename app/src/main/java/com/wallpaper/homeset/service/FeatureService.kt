package com.wallpaper.homeset.service

import android.Manifest
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.wallpaper.homeset.callback.DownloadCallback
import com.wallpaper.homeset.callback.OnActionListener
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.ui.TheApplication
import com.wallpaper.homeset.util.Utility
import java.io.File
import java.io.IOException
import java.util.*

class FeatureService {

    private val TAG = "FeatureService";

    val downloadStartMsg = "Downloading Wallpaper"
    val setWallpaperMsg = "Setting Up Home"
    val wallpaperApplied = "Check your HomeScreen"
    val downloadComplete = "Download Complete"
    val wallpaperAppliedError = "Can't set Wallpaper"
    val downloadError = "Download Failure"


    fun setWallpaper(data: EntityPhoto, onActionListener: OnActionListener) {
        checkPermission(object : PermissionListener {
            override fun onPermissionGranted() {
                val entityUrl = data.entityUrl
                var fullThumbUrl: String? = ""
                if (entityUrl != null) {
                    fullThumbUrl = entityUrl.full
                }

                if (TextUtils.isEmpty(fullThumbUrl)) {
                    onActionListener.showToast(wallpaperAppliedError)
                    return
                }
                downloadFile(fullThumbUrl!!, data.id, true, onActionListener)
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            }
        })
    }

    private fun checkPermission(permissionListener: PermissionListener) {
        TedPermission.with(TheApplication.mInstance)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("You Can't Download the wallpaper")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    fun downloadWallpaper(data: Any, onActionListener: OnActionListener) {
        checkPermission(object : PermissionListener {
            override fun onPermissionGranted() {
                if (data is EntityPhoto) {
                    val entityUrl = data.entityUrl
                    var fullThumbUrl: String? = ""
                    if (entityUrl != null) {
                        fullThumbUrl = entityUrl.full
                    }
                    if (TextUtils.isEmpty(fullThumbUrl)) {
                        onActionListener.showToast(downloadError)
                        return
                    }
                    downloadFile(fullThumbUrl!!, data.id, false, onActionListener)
                } else {
                    onActionListener.showToast(downloadError)
                }
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            }
        })
    }

    private fun loadBitmap(path: String, onActionListener: OnActionListener) {
        onActionListener.showProgressDialog(true)
        onActionListener.setProgressMsg(setWallpaperMsg)
        Glide.with(TheApplication.mInstance)
                .asBitmap()
                .load(path)
                .into(object : SimpleTarget<Bitmap>(Utility.screenWidth, Utility.screenHeight) {
//                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
//                        val myWallpaperManager = WallpaperManager.getInstance(TheApplication.mInstance)
//                        try {
//                            myWallpaperManager.setBitmap(resource)
//                            onActionListener.showProgressDialog(false)
//                            onActionListener.showToast(wallpaperApplied)
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                            onActionListener.showProgressDialog(false)
//                            onActionListener.showToast(wallpaperAppliedError)
//                        }
//
//                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        onActionListener.showProgressDialog(false)
                        onActionListener.showToast(wallpaperAppliedError)
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val myWallpaperManager = WallpaperManager.getInstance(TheApplication.mInstance)
                        try {
                            myWallpaperManager.setBitmap(resource)
                            onActionListener.showProgressDialog(false)
                            onActionListener.showToast(wallpaperApplied)
                        } catch (e: IOException) {
                            e.printStackTrace()
                            onActionListener.showProgressDialog(false)
                            onActionListener.showToast(wallpaperAppliedError)
                        }
                    }
                })
    }

    private fun downloadFile(url: String, fileName: String?, setWallpaper: Boolean, onActionListener: OnActionListener) {
        val rootFolder = File(Environment.getExternalStorageDirectory(), "HomeSet")
        if (!rootFolder.exists()) {
            rootFolder.mkdir()
        }
        val file = File(rootFolder, fileName!! + ".jpg")

        onActionListener.showProgressDialog(true)
        onActionListener.setProgressMsg(downloadStartMsg)

        DownloadService.getInstance().downloadFile(url, rootFolder.absolutePath, "$fileName.jpg"
                , object : DownloadCallback {
            override fun onDownloadComplete() {
                onActionListener.showProgressDialog(false)
                onActionListener.showToast(downloadComplete)
                scanMedia(file, setWallpaper, onActionListener)
            }

            override fun onError() {
                Log.wtf(TAG, "onDownloadFailed")

                onActionListener.showProgressDialog(false)
                onActionListener.showToast(downloadError)
            }

            override fun setProgress(progress: Int) {
            }

        })
    }

    private fun scanMedia(file: File, setWallpaper: Boolean, onActionListener: OnActionListener) {
        MediaScannerConnection.scanFile(TheApplication.mInstance,
                arrayOf(file.absolutePath), arrayOf("image/*")
        ) { path, uri ->
            Utility.runOnUIThread(Runnable {
                if (setWallpaper) {
                    loadBitmap(file.absolutePath, onActionListener)
                }
            })
        }
    }

}