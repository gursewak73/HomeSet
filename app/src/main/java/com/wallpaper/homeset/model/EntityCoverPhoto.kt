package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class EntityCoverPhoto() : Parcelable {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("color")
    var color: String? = ""

    @SerializedName("user")
    var user: EntityUser? = null

    @SerializedName("categories")
    var categoryList: ArrayList<EntityCategories> = ArrayList()

    @SerializedName("links")
    var links: EntityLinks? = null

    @SerializedName("urls")
    var url: EntityUrl? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        color = parcel.readString()
        user = parcel.readParcelable(EntityUser::class.java.classLoader)
        links = parcel.readParcelable(EntityLinks::class.java.classLoader)
        url = parcel.readParcelable(EntityUrl::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(color)
        parcel.writeParcelable(user, flags)
        parcel.writeParcelable(links, flags)
        parcel.writeParcelable(url, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityCoverPhoto> {
        override fun createFromParcel(parcel: Parcel): EntityCoverPhoto {
            return EntityCoverPhoto(parcel)
        }

        override fun newArray(size: Int): Array<EntityCoverPhoto?> {
            return arrayOfNulls(size)
        }
    }

}
