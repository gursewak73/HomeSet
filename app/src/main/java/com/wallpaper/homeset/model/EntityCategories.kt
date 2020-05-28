package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityCategories {

    @SerializedName("id")
    val id: String = ""

    @SerializedName("title")
    val title: String = ""

    @SerializedName("photo_count")
    val photoCount: Int = 0

    @SerializedName("links")
    val links: EntityLinks? = null
}
