package com.example.pathseen.serverGames.repository

import com.example.pathseen.serverGames.RAWG
import com.example.pathseen.serverGames.model.GamesList


class GamesServerRepository {
    private val key = "faf1932413f24c56881df86046bede61"
    suspend fun loadMovies() : GamesList = RAWG.retrofit.loadMovies(key)
}