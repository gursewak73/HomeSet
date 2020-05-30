package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityLinks() : Parcelable {

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

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityLinks> {
        override fun createFromParcel(parcel: Parcel): EntityLinks {
            return EntityLinks(parcel)
        }

        override fun newArray(size: Int): Array<EntityLinks?> {
            return arrayOfNulls(size)
        }
    }
}