package com.wallpaper.homeset.util

import android.content.res.Resources
import android.graphics.Color
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_grid.view.*

object Utility {

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun runOnUIThread(runnable: Runnable) {
        val UIHandler = android.os.Handler(Looper.getMainLooper())
        UIHandler.post(runnable)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

    @BindingAdapter("imageBg")
    @JvmStatic
    fun loadBackgroundColor(view: View, color : String?) {
        if (color != null && !TextUtils.isEmpty(color)) {
            view.iv_photo.setBackgroundColor(Color.parseColor(color))
        }
    }
}