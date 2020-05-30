package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/26/2017.
 */

class EntityUrl() : Parcelable {

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

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityUrl> {
        override fun createFromParcel(parcel: Parcel): EntityUrl {
            return EntityUrl(parcel)
        }

        override fun newArray(size: Int): Array<EntityUrl?> {
            return arrayOfNulls(size)
        }
    }
}
