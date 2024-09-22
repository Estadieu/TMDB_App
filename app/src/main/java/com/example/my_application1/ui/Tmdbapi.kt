package com.example.my_application1.ui

import com.example.my_application1.ui.TmdbMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Tmdbapi{

    //Films
    @GET("trending/movie/week")
    suspend fun lastmoviesOfWeek(@Query("api_key") api_key: String): TmdbMovieResult


    @GET("search/movie/")
    suspend fun searchMovie(@Query("query") keyWord: String, @Query("api_key") api_key: String) : TmdbMovieResult

    //Séries
    @GET("trending/tv/week")
    suspend fun lastserieOfWeek(@Query("api_key") api_key: String): Series

    @GET("trending/person/week")
    suspend fun lastactorOfWeek(@Query("api_key") api_key: String): Actors

    @GET("tv/{id}")
    suspend fun overviewOfSerie(@Path("id") id: String, @Query("api_key") api_key: String): DetailedSerie

    @GET("search/person")
    suspend fun searchActor(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Actors
}
