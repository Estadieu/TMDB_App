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
}