package com.wallpaper.homeset.ui.di

import androidx.lifecycle.ViewModel
import com.wallpaper.homeset.repository.MainRepository
import com.wallpaper.homeset.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel : MainViewModel) : ViewModel
}