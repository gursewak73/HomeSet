package com.wallpaper.homeset.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.showShortToast(msg: String) {
    if (msg.isNotEmpty()) {
        Toast.makeText(
            this, msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}