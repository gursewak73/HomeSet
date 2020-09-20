package com.wallpaper.homeset.ui.di

import com.wallpaper.homeset.network.RetrofitBuilder
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideNetworkService() = RetrofitBuilder.apiService
}