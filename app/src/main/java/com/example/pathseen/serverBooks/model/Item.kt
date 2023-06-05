package com.example.pathseen.serverBooks.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("volumeInfo")
    val bookServer: BookServer
)