package com.example.pathseen.serverBooks

import com.example.pathseen.serverBooks.model.BooksList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes?q=subject:fiction&orderBy=relevance&maxResults=40&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/averageRating,volumeInfo/imageLinks/thumbnail)")
    suspend fun loadBooks(@Query("key") key: String) : BooksList
}