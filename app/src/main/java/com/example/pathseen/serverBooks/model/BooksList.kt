package com.example.pathseen.serverBooks.model


import com.google.gson.annotations.SerializedName

data class BooksList(
    @SerializedName("items")
    val items: List<Item>
)