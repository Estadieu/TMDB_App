package com.example.my_application1.ui.Model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(val fiche: TmdbMovie, @PrimaryKey val id: String)

