package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/26/2017.
 */

class EntityUrl() : Parcelable {

    @SerializedName("raw")
    var raw: String? = ""

    @SerializedName("full")
    var full: String? = ""

    @SerializedName("regular")
    var regular: String? = ""

    @SerializedName("small")
    var small: String? = ""

    @SerializedName("thumb")
    var thumb: String? = ""

    constructor(parcel: Parcel) : this() {
        raw = parcel.readString()
        full = parcel.readString()
        regular = parcel.readString()
        small = parcel.readString()
        thumb = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raw)
        parcel.writeString(full)
        parcel.writeString(regular)
        parcel.writeString(small)
        parcel.writeString(thumb)
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
