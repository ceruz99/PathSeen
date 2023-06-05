package com.example.pathseen.serverGames.model


import com.google.gson.annotations.SerializedName

data class RequirementsEn(
    @SerializedName("minimum")
    val minimum: String,
    @SerializedName("recommended")
    val recommended: String?
)