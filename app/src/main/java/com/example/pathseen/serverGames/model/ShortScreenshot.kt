package com.example.pathseen.serverGames.model


import com.google.gson.annotations.SerializedName

data class ShortScreenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)