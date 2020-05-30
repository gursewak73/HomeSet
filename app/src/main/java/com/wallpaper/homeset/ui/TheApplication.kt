package com.wallpaper.homeset.ui

import android.app.Application

class TheApplication : Application() {

    companion object {
        lateinit var mInstance: TheApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}