package com.wallpaper.homeset.callback

/**
 * Created by gursewaksingh on 25/02/18.
 */

interface ICallback<T> {
    fun onSuccess(t : T);
    fun onFailure(msg : String);
}