package com.wallpaper.homeset.callback

interface OnActionListener {
    fun showProgressDialog(show: Boolean)
    fun setProgressMsg(msg: String)
    fun showToast(msg : String)
}