package com.example.pathseen.serverMovies.repository

import com.example.pathseen.serverMovies.MovieDB
import com.example.pathseen.serverMovies.model.MoviesList

class MoviesRepository {
    private val apikey = "8011f4d2ee79071c844bb34c146e92dc"
    suspend fun loadMovies() : MoviesList = MovieDB.retrofit.loadMovies(apikey)
}