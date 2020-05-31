package com.wallpaper.homeset.util

import android.content.Context
import android.content.res.Resources
import android.os.Looper
import android.widget.Toast

object Utility {

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun runOnUIThread(runnable: Runnable) {
        val UIHandler = android.os.Handler(Looper.getMainLooper())
        UIHandler.post(runnable)
    }
}