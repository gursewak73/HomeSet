package com.wallpaper.homeset.ui

import android.app.Application
import com.wallpaper.homeset.ui.di.AppComponent
import com.wallpaper.homeset.ui.di.DaggerAppComponent

class TheApplication : Application() {

    companion object {
        lateinit var mInstance: TheApplication
            private set
    }

     val appComponent : AppComponent by lazy {
         initializeComponent()
     }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    private fun initializeComponent()  : AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}