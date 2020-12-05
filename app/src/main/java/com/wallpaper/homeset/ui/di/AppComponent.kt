package com.wallpaper.homeset.ui.di

import android.content.Context
import com.wallpaper.homeset.ui.activity.FullScreenActivityNew
import com.wallpaper.homeset.ui.activity.HomeActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [NetworkModule::class,
        AppModuleBinds::class,
        OtherModule::class,
        ViewModelBuilderModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(activity: HomeActivity)

    fun inject(activity: FullScreenActivityNew)
}