package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class EntityProfileImage() : Parcelable {

    @SerializedName("small")
    var small: String? = ""

    @SerializedName("medium")
    var medium: String? = ""

    @SerializedName("large")
    var large: String? = ""

    constructor(parcel: Parcel) : this() {
        small = parcel.readString()
        medium = parcel.readString()
        large = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(small)
        parcel.writeString(medium)
        parcel.writeString(large)
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
