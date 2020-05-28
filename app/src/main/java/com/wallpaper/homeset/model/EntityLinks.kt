package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityLinks {

    @SerializedName("self")
    val self: String? = ""

    @SerializedName("html")
    val html: String? = ""

    @SerializedName("photos")
    val photos: String? = ""

    @SerializedName("likes")
    val likes: String? = ""

    @SerializedName("portfolio")
    val portfolio: String? = ""

    @SerializedName("download")
    val download: String? = ""
}