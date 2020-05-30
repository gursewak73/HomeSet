package com.wallpaper.homeset.util

import android.content.Context
import android.content.res.Resources
import android.os.Looper
import android.widget.Toast

object Utility {

    fun Toast.showToast(context : Context, msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun runOnUIThread(runnable: Runnable) {
        val UIHandler = android.os.Handler(Looper.getMainLooper())
        UIHandler.post(runnable)
    }
}