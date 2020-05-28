package com.wallpaper.homeset.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Gursewak on 1/28/2017.
 */

class EntityUser {

    @SerializedName("id")
    val id: String? = ""

    @SerializedName("username")
    val username: String? = ""

    @SerializedName("name")
    val name: String? = ""

    @SerializedName("bio")
    val bio: String? = ""

    @SerializedName("location")
    val location: String? = ""

    @SerializedName("total_likes")
    val total_likes: String? = ""

    @SerializedName("total_photos")
    val total_photos: String? = ""

    @SerializedName("total_collections")
    val total_collections: String? = ""

    @SerializedName("portfolio_url")
    val portfolioUrl: String? = ""

    @SerializedName("profile_image")
    val profileImage: EntityProfileImage? = null

    @SerializedName("links")
    val links: EntityLinks? = null


}
