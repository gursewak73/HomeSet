package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by gursewaksingh on 05/02/17.
 */

class EntityCategories() : Parcelable {

    @SerializedName("id")
    val id: String = ""

    @SerializedName("title")
    val title: String = ""

    @SerializedName("photo_count")
    val photoCount: Int = 0

    @SerializedName("links")
    val links: EntityLinks? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

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
