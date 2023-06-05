package com.example.pathseen.serverGames

import com.example.pathseen.serverGames.model.GamesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games?ordering=-added&page_size=10&fields=name,rating,developers,background_image")
    suspend fun loadMovies(@Query("key") key: String) : GamesList
}