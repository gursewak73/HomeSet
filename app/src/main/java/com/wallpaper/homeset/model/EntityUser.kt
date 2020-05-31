package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class EntityUser() : Parcelable {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("username")
    var username: String? = ""

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("bio")
    var bio: String? = ""

    @SerializedName("location")
    var location: String? = ""

    @SerializedName("total_likes")
    var total_likes: String? = ""

    @SerializedName("total_photos")
    var total_photos: String? = ""

    @SerializedName("total_collections")
    var total_collections: String? = ""

    @SerializedName("portfolio_url")
    var portfolioUrl: String? = ""

    @SerializedName("profile_image")
    var profileImage: EntityProfileImage? = null

    @SerializedName("links")
    var links: EntityLinks? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        username = parcel.readString()
        name = parcel.readString()
        bio = parcel.readString()
        location = parcel.readString()
        total_likes = parcel.readString()
        total_photos = parcel.readString()
        total_collections = parcel.readString()
        portfolioUrl = parcel.readString()
        profileImage = parcel.readParcelable(EntityProfileImage::class.java.classLoader)
        links = parcel.readParcelable(EntityLinks::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(bio)
        parcel.writeString(location)
        parcel.writeString(total_likes)
        parcel.writeString(total_photos)
        parcel.writeString(total_collections)
        parcel.writeString(portfolioUrl)
        parcel.writeParcelable(profileImage, flags)
        parcel.writeParcelable(links, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntityUser> {
        override fun createFromParcel(parcel: Parcel): EntityUser {
            return EntityUser(parcel)
        }

        override fun newArray(size: Int): Array<EntityUser?> {
            return arrayOfNulls(size)
        }
    }


}
