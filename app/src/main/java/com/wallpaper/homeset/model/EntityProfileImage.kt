package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityProfileImage {

    @SerializedName("small")
    val small: String? = ""

    @SerializedName("medium")
    val medium: String? = ""

    @SerializedName("large")
    val large: String? = ""
}
