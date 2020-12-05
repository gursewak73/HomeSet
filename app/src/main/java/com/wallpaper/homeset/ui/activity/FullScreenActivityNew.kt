package com.wallpaper.homeset.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wallpaper.homeset.R
import com.wallpaper.homeset.databinding.ActivityFullScreenNewBinding
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.ui.TheApplication
import com.wallpaper.homeset.util.showShortToast
import com.wallpaper.homeset.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_full_screen_new.*
import javax.inject.Inject

class FullScreenActivityNew : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenNewBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    companion object {
        const val POSITION = "position"
        const val PHOTO_LIST = "photo_list"
    }

    private var dialog: AlertDialog? = null
    private var dialogMsg: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TheApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_new)

        val list = intent?.getParcelableArrayListExtra<EntityPhoto>(PHOTO_LIST)
        val position = intent?.getIntExtra(POSITION, 0)

        if (list != null && position != null) {

            setUpDialog()

            var thumbUrl : String? = list[position].entityUrl?.regular
            if (thumbUrl == null) {
                thumbUrl = list[position].coverPhoto?.url?.regular!!
            }
            binding.imageUrl = thumbUrl
            binding.executePendingBindings()

            set_wallpaper_btn.setOnClickListener {
                viewModel.setWallpaper(list[position])
            }

            viewModel.setWallpaper.observe(this, Observer {
                wallpaperSetAction(featureModel = it)
            })
        }
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