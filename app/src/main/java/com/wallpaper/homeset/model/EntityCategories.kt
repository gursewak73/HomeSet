package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class EntityCategories() : Parcelable {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("photo_count")
    var photoCount: Int = 0

    @SerializedName("links")
    var links: EntityLinks? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        photoCount = parcel.readInt()
        links = parcel.readParcelable(EntityLinks::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeInt(photoCount)
        parcel.writeParcelable(links, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityCategories> {
        override fun createFromParcel(parcel: Parcel): EntityCategories {
            return EntityCategories(parcel)
        }

        override fun newArray(size: Int): Array<EntityCategories?> {
            return arrayOfNulls(size)
        }
    }

}
