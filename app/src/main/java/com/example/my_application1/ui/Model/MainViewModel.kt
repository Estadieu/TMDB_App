package com.example.my_application1.ui.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_application1.ui.Api.Tmdbapi
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val movies_select = MutableStateFlow<DetailedMovie>(DetailedMovie())
    val series_select = MutableStateFlow<DetailedSerie>(DetailedSerie())
    val movieCast = MutableStateFlow<List<Cast>>(emptyList())
    val seriesCast = MutableStateFlow<List<CastSerie>>(emptyList())

    fun getMovies() {
        viewModelScope.launch { movies.value = repo.lastMovies() }
    }

    //Pour les Films
    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les films populaires
                movies.value = repo.lastMovies()
            } else {
                // Sinon, on filtre les films par le mot-clé
                movies.value = repo.searchMoviesByKeyword(motcle)
            }
        }
    }

    //Pour les Series
    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les series
                series.value = repo.lastSeries()
            } else {
                // Sinon, on filtre les series par le mot-clé
                series.value = repo.searchSeriesByKeyword(motcle)
            }

        }
    }

    //Pour les Acteurs
    fun searchActors(motcle: String) {
        viewModelScope.launch {
            if (motcle.isBlank()) {
                // Si aucun mot-clé n'est fourni, on affiche les acteurs
                actors.value = repo.lastActors()
            } else {
                // Sinon, on filtre les series par le mot-clé
                actors.value = repo.searchActorsByKeyword(motcle)
            }

        }
    }

    //Pour selection séries et films
    fun selectedMovies(id : Int){
        viewModelScope.launch {
            movies_select.value = repo.selectOfMovie(id)
        }
    }
    fun selectedSeries(id : Int){
        viewModelScope.launch {
            series_select.value = repo.selectOfSerie(id)
        }
    }

    // Pour afficher les acteurs d'une série ou d'un film select
    fun getActeurMovie(id: Int) {
        viewModelScope.launch {
            try {
                val result = repo.acteurfilm(id)
                movieCast.value = result.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun getActeurSeries(id: Int) {
        viewModelScope.launch {
            try {
                val serieact = repo.acteurseries(id)
                seriesCast.value = serieact.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}