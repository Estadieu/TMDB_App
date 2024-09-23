package com.example.my_application1.ui
import com.example.my_application1.ui.Tmdbapi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_application1.ui.TmdbMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel: ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmoviesOfWeek("").results
        }
    }
    /*
    fun searchMovie(texte: String) {
        viewModelScope.launch {
            movies.value = api.searchMovie(texte, "").results
        }
    }
    */

}