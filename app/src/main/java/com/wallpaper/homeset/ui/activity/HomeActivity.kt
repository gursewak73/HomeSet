package com.wallpaper.homeset.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.wallpaper.homeset.R
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.network.RetrofitBuilder
import com.wallpaper.homeset.viewmodel.MainViewModel
import com.wallpaper.homeset.viewmodel.ViewModelFactory


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(APIHelper(RetrofitBuilder.apiService)))
                .get(MainViewModel::class.java)
    }
}