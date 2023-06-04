package com.example.pathseen.serverMovies

import com.example.pathseen.serverMovies.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated?")
    suspend fun loadMovies(@Query("api_key") api_key: String) : MoviesList
}