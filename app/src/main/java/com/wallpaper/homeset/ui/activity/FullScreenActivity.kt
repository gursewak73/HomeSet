package com.wallpaper.homeset.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wallpaper.homeset.R
import com.wallpaper.homeset.api.APIHelper
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.network.RetrofitBuilder
import com.wallpaper.homeset.service.FeatureService
import com.wallpaper.homeset.ui.adapter.AdapterFullScreenView
import com.wallpaper.homeset.util.showShortToast
import com.wallpaper.homeset.viewmodel.MainViewModel
import com.wallpaper.homeset.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_full_screen.*

class FullScreenActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
            ViewModelProviders.of(this, ViewModelFactory(APIHelper(RetrofitBuilder.apiService), FeatureService()))
                .get(MainViewModel::class.java)
    }

    companion object {
        const val POSITION = "position"
        const val PHOTO_LIST = "photo_list"
    }

    private var dialog: AlertDialog? = null
    private var dialogMsg: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_full_screen)
        setUpDialog()
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
        viewModel.setWallpaper.observe(this, Observer {
            wallpaperSetAction(featureModel = it)
        })

    }

    private fun wallpaperSetAction(featureModel: FeatureModel) {
        when (featureModel.action) {
            FeatureModel.Action.PROGRESS -> {
                Log.d("PROGRESS", "" + featureModel.show)
                if (featureModel.show) {
                    showDialog(msg = featureModel.msg)
                } else {
                    hideDialog()
                }
            }

            FeatureModel.Action.TOAST -> {
                if (dialog?.isShowing!!) dialog?.hide()
                showShortToast(featureModel.msg)
            }
        }
    }

    private fun showDialog(msg: String) {
        dialog?.show()
        if (!TextUtils.isEmpty(msg)) {
            dialogMsg?.text = msg
        }
    }

    private fun hideDialog() {
        if (dialog?.isShowing!!) {
            dialog?.hide()
        }
    }

    private fun setUpDialog() {
        val builder = AlertDialog.Builder(this)
        val inflate = LayoutInflater.from(this).inflate(R.layout.layout_progress, null)
        builder.setView(inflate)
        dialogMsg = inflate.findViewById<TextView>(R.id.tvMsg)
        dialog = builder.create()
    }
}