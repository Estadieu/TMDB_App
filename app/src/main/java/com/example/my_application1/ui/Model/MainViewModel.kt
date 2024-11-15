package com.example.my_application1.ui.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_application1.ui.Api.Tmdbapi
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
    val movieCast = MutableStateFlow<List<Cast>>(emptyList())
    val seriesCast = MutableStateFlow<List<CastSerie>>(emptyList())
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

    //Pour selection séries et films
    fun selectedMovies(id : Int){
        viewModelScope.launch {
            movies_select.value = service.selectOfMovie(id,api_key)
        }
    }
    fun selectedSeries(id : Int){
        viewModelScope.launch {
            series_select.value = service.selectOfSerie(id,api_key)
        }
    }

    // Pour afficher les acteurs d'une série ou d'un film select
    fun getActeurMovie(id: Int) {
        viewModelScope.launch {
            try {
                val result = service.acteurfilm(id, api_key)
                movieCast.value = result.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun getActeurSeries(id: Int) {
        viewModelScope.launch {
            try {
                val serieact = service.acteurseries(id, api_key)
                seriesCast.value = serieact.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}