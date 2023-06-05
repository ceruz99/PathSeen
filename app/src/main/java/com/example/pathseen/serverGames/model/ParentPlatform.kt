package com.example.pathseen.serverGames.model


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: Platform
)