package com.example.my_application1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_application1.ui.Tmdbapi
import com.example.my_application1.ui.Actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ActorViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val acteurs = MutableStateFlow<List<Actor>>(listOf())

    init {
        getActors()
    }

    fun getActors() {
        viewModelScope.launch {
            acteurs.value = api.lastactorOfWeek("").results
        }
    }

    fun searchActors(texte: String) {
        viewModelScope.launch {
            acteurs.value = api.searchActor(texte, "").results
        }
    }
}