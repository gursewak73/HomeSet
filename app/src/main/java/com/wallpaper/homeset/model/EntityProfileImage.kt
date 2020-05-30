package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityProfileImage() : Parcelable {

    @SerializedName("small")
    val small: String? = ""

    @SerializedName("medium")
    val medium: String? = ""

    @SerializedName("large")
    val large: String? = ""

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityProfileImage> {
        override fun createFromParcel(parcel: Parcel): EntityProfileImage {
            return EntityProfileImage(parcel)
        }

        override fun newArray(size: Int): Array<EntityProfileImage?> {
            return arrayOfNulls(size)
        }
    }
}
