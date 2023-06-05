package com.example.pathseen.serverBooks.model


import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("thumbnail")
    val thumbnail: String
)