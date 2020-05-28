package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by Gursewak on 1/28/2017.
 */

class EntityCoverPhoto {

    @SerializedName("id")
    val id: String? = ""

    @SerializedName("color")
    val color: String? = ""

    @SerializedName("user")
    val user: EntityUser? = null

    @SerializedName("categories")
    val categoryList: ArrayList<EntityCategories> = ArrayList()

    @SerializedName("links")
    val links: EntityLinks? = null

    @SerializedName("urls")
    val url: EntityUrl? = null
}
