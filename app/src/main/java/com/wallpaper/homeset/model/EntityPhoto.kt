package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/26/2017.
 */

class EntityPhoto() : Parcelable {

    @SerializedName("id")
    val id: String? = ""

    @SerializedName("created_at")
    val created_at: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("width")
    val width: Int = 0

    @SerializedName("height")
    val height: Int = 0

    @SerializedName("color")
    val color: String? = ""

    @SerializedName("urls")
    val entityUrl: EntityUrl? = null

    @SerializedName("cover_photo")
    val coverPhoto: EntityCoverPhoto? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is EntityPhoto) {
            return id == other.id
        }
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityPhoto> {
        override fun createFromParcel(parcel: Parcel): EntityPhoto {
            return EntityPhoto(parcel)
        }

        override fun newArray(size: Int): Array<EntityPhoto?> {
            return arrayOfNulls(size)
        }
    }
}
