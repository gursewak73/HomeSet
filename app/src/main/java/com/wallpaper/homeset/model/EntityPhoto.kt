package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/26/2017.
 */

class EntityPhoto {

    @SerializedName("id")
    val id: String? = ""

    @SerializedName("created_at")
    val created_at: String? = ""

    @SerializedName("title")
    val title: String? = ""

    @SerializedName("width")
    val width: Int = 0

    @SerializedName("height")
    val height: Int = 0

    @SerializedName("color")
    val color: String? = ""

    @SerializedName("urls")
    val entityUrl: EntityUrl? = null

    @SerializedName("cover_photo")
    val coverPhoto: EntityCoverPhoto? = null

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
