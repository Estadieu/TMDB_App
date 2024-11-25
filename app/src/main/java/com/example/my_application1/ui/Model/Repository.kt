package com.example.my_application1.ui.Model

import com.example.my_application1.ui.Api.Tmdbapi


class Repository(val tmdbapi: Tmdbapi) {
    val api_key = "a6f34ffd317094fe364b44e6dbd6d5bc"

    suspend fun lastMovies() = tmdbapi.lastmoviesOfWeek(api_key).results
    suspend fun lastSeries() = tmdbapi.lastserieOfWeek(api_key).results
    suspend fun lastActors() = tmdbapi.lastactorOfWeek(api_key).results
    // Pour la recherche
    suspend fun searchMoviesByKeyword(keyword: String) = tmdbapi.getFilmParMotsCle(keyword, api_key).results
    suspend fun searchSeriesByKeyword(keyword: String) = tmdbapi.getSerieParMotsCle(keyword, api_key).results
    suspend fun searchActorsByKeyword(keyword: String) = tmdbapi.getActorsParMotsCle(keyword, api_key).results

    //Pour mes selected
    suspend fun selectOfMovie(id: Int) = tmdbapi.selectOfMovie(id, api_key)
    suspend fun selectOfSerie(id: Int) = tmdbapi.selectOfSerie(id, api_key)

    suspend fun acteurfilm(id: Int) = tmdbapi.acteurfilm(id, api_key)

    suspend fun acteurseries(id: Int) = tmdbapi.acteurseries(id, api_key)

}