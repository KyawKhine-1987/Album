package com.android.freelance.album.data.db.network.models


import com.google.gson.annotations.SerializedName

data class MusicAlbums(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("artist")
    val artist: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("thumbnail_image")
    val thumbnailImage: String? = null
)
// ?= null is can be null