package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class EntityPhoto() : Parcelable {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("created_at")
    var created_at: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("width")
    var width: Int = 0

    @SerializedName("height")
    var height: Int = 0

    @SerializedName("color")
    var color: String? = ""

    @SerializedName("urls")
    var entityUrl: EntityUrl? = null

    @SerializedName("cover_photo")
    var coverPhoto: EntityCoverPhoto? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        created_at = parcel.readString()
        title = parcel.readString()
        width = parcel.readInt()
        height = parcel.readInt()
        color = parcel.readString()
        entityUrl = parcel.readParcelable(EntityUrl::class.java.classLoader)
        coverPhoto = parcel.readParcelable(EntityCoverPhoto::class.java.classLoader)
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
        parcel.writeString(id)
        parcel.writeString(created_at)
        parcel.writeString(title)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(color)
        parcel.writeParcelable(entityUrl, flags)
        parcel.writeParcelable(coverPhoto, flags)
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
