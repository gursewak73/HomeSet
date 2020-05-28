package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/26/2017.
 */

class EntityUrl {

    @SerializedName("raw")
    val raw: String? = ""

    @SerializedName("full")
    val full: String? = ""

    @SerializedName("regular")
    val regular: String? = ""

    @SerializedName("small")
    val small: String? = ""

    @SerializedName("thumb")
    val thumb: String? = ""
}
