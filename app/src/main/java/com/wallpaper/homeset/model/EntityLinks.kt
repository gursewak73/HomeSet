package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class EntityLinks() : Parcelable {

    @SerializedName("self")
    var self: String? = ""

    @SerializedName("html")
    var html: String? = ""

    @SerializedName("photos")
    var photos: String? = ""

    @SerializedName("likes")
    var likes: String? = ""

    @SerializedName("portfolio")
    var portfolio: String? = ""

    @SerializedName("download")
    var download: String? = ""

    constructor(parcel: Parcel) : this() {
        self = parcel.readString()
        html = parcel.readString()
        photos = parcel.readString()
        likes = parcel.readString()
        portfolio = parcel.readString()
        download = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(photos)
        parcel.writeString(likes)
        parcel.writeString(portfolio)
        parcel.writeString(download)
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