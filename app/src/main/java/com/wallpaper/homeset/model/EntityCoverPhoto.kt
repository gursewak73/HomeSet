package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by Gursewak on 1/28/2017.
 */

class EntityCoverPhoto() : Parcelable {

    @SerializedName("id")
    val id: String? = ""

    @SerializedName("color")
    val color: String? = ""

    @SerializedName("user")
    val user: EntityUser? = null

    @SerializedName("categories")
    val categoryList: ArrayList<EntityCategories> = ArrayList()

    @SerializedName("links")
    val links: EntityLinks? = null

    @SerializedName("urls")
    val url: EntityUrl? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

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
