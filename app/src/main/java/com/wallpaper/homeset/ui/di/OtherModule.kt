package com.wallpaper.homeset.ui.di

import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.repository.MainRepository
import com.wallpaper.homeset.service.FeatureService
import dagger.Module
import dagger.Provides

@Module
class OtherModule {

    @Provides
    fun provideRepository(apiHelper: APIHelper, featureService: FeatureService) : MainRepository {
        return MainRepository(apiHelper, featureService)
    }
}