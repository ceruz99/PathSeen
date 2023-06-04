package com.example.pathseen.serverMovies

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDB {
    private const val urlAPI = "https://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    val retrofit: ApiService = Retrofit.Builder().baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build().run {
            create(ApiService::class.java)
        }
}