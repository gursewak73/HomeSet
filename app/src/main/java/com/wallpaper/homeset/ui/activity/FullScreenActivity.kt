package com.wallpaper.homeset.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wallpaper.homeset.R
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.network.RetrofitBuilder
import com.wallpaper.homeset.ui.adapter.AdapterFullScreenView
import com.wallpaper.homeset.viewmodel.MainViewModel
import com.wallpaper.homeset.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_full_screen.*

class FullScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    companion object {
        const val POSITION = "position"
        const val PHOTO_LIST = "photo_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_full_screen)
        val list = intent?.getParcelableArrayListExtra<EntityPhoto>(PHOTO_LIST)
        list?.let {
            viewPager.adapter = AdapterFullScreenView(supportFragmentManager, it)
            set_wallpaper_btn.setOnClickListener {
                viewModel.setWallpaper(list[viewPager.currentItem])
            }

            intent?.getIntExtra(POSITION, 0)?.let { position ->
                viewPager.currentItem = position
            }
        }

        setUpViewModel()

        viewModel.setWallpaper.observe(this, Observer {
            wallpaperSetAction(featureModel = it)
        })

    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(APIHelper(RetrofitBuilder.apiService)))
                .get(MainViewModel::class.java)
    }

    private fun wallpaperSetAction(featureModel: FeatureModel) {
        when (featureModel.action) {
            FeatureModel.Action.PROGRESS -> {
                Log.d("PROGRESS", "" + featureModel.show)
            }

            FeatureModel.Action.TOAST -> {
                Toast.makeText(this, featureModel.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}