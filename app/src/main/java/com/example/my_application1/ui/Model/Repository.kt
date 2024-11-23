package com.example.my_application1.ui.Model

import com.example.my_application1.ui.Api.Tmdbapi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository() {
    val tmdbapi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(Tmdbapi::class.java)
    suspend fun lastMovies() = tmdbapi.lastmoviesOfWeek("a6f34ffd317094fe364b44e6dbd6d5bc").results
}