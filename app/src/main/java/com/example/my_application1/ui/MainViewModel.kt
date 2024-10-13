package com.example.my_application1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val movies_select = MutableStateFlow<DetailedMovie>(DetailedMovie())
    val series_select = MutableStateFlow<DetailedSerie>(DetailedSerie())
    val api_key = "a6f34ffd317094fe364b44e6dbd6d5bc"


    val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(intercepter)
    }.build()

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(Tmdbapi::class.java);

    //Pour les Films
    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les films populaires
                movies.value = service.lastmoviesOfWeek(api_key).results
            } else {
                // Sinon, on filtre les films par le mot-clé
                movies.value = service.getFilmParMotsCle(motcle, api_key).results
            }
        }
    }

    //Pour les Series
    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les series
                series.value = service.lastserieOfWeek(api_key).results
            } else {
                // Sinon, on filtre les series par le mot-clé
                series.value = service.getSerieParMotsCle(motcle, api_key).results
            }

        }
    }

    //Pour les Acteurs
    fun searchActors(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les acteurs
                actors.value = service.lastactorOfWeek(api_key).results
            } else {
                // Sinon, on filtre les series par le mot-clé
                actors.value = service.getActorsParMotsCle(motcle, api_key).results
            }

        }
    }

    fun selectedMovies(id : String){
        viewModelScope.launch {
            movies_select.value = service.selectOfMovie(id,api_key)
        }
    }

    fun selectedSeries(id : String){
        viewModelScope.launch {
            series_select.value = service.selectOfSerie(id,api_key)
        }
    }

    

}