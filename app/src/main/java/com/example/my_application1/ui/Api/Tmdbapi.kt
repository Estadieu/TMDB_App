package com.example.my_application1.ui.Api

import com.example.my_application1.ui.Model.Actors
import com.example.my_application1.ui.Model.DetailedMovie
import com.example.my_application1.ui.Model.DetailedSerie
import com.example.my_application1.ui.Model.MovieCreditsResult
import com.example.my_application1.ui.Model.MoviesActeurs
import com.example.my_application1.ui.Model.Series
import com.example.my_application1.ui.Model.TmdbMovieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Tmdbapi{

    //Films
    @GET("trending/movie/week")
    suspend fun lastmoviesOfWeek(@Query("api_key") api_key: String): TmdbMovieResult
    @GET("search/movie")
    suspend fun getFilmParMotsCle(@Query("query") keyWord: String, @Query("api_key") api_key: String) : TmdbMovieResult
    @GET("movie/{id}")
    suspend fun selectOfMovie(@Path("id") id: Int, @Query("api_key") api_key: String): DetailedMovie
    //Pour afficher les acteurs d'un film
    @GET("movie/{id}/credits")
    suspend fun acteurfilm(@Path("id") id: Int, @Query("api_key") api_Key: String): MovieCreditsResult

    //Séries
    @GET("trending/tv/week")
    suspend fun lastserieOfWeek(@Query("api_key") api_key: String): Series
    @GET("search/movie")
    suspend fun getSerieParMotsCle(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Series
    @GET("tv/{id}")
    suspend fun selectOfSerie(@Path("id") id: Int, @Query("api_key") api_key: String): DetailedSerie

    //Actors
    @GET("trending/person/week")
    suspend fun lastactorOfWeek(@Query("api_key") api_key: String): Actors
    @GET("search/person")
    suspend fun getActorsParMotsCle(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Actors
}
