package com.example.pathseen.serverBooks.model


import com.google.gson.annotations.SerializedName

data class BookServer(
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("averageRating")
    val averageRating: Double?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?,
    @SerializedName("title")
    val title: String
)