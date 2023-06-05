package com.example.pathseen.serverBooks.repository

import com.example.pathseen.serverBooks.GBooks
import com.example.pathseen.serverBooks.model.BooksList


class BooksServerRepository {
    private val key = "AIzaSyCWUDsDpU03XK49_ByBqi2LUwveD8UplII"
    suspend fun loadBooks() : BooksList = GBooks.retrofit.loadBooks(key)

}