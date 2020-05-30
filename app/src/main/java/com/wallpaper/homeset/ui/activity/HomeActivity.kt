package com.wallpaper.homeset.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.wallpaper.homeset.R
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.network.RetrofitBuilder
import com.wallpaper.homeset.viewmodel.MainViewModel
import com.wallpaper.homeset.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setUpViewModel()
        visibilityNavElements()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(APIHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }

    private fun visibilityNavElements() {
        Navigation.findNavController(this, R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fullScreenFragment -> app_bar.visibility = View.GONE
                else -> app_bar.visibility = View.VISIBLE
            }
        }
    }
}