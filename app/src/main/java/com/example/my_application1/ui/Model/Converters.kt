package com.example.my_application1.ui.Model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    val filmJsonadapter = moshi.adapter(TmdbMovie::class.java)

    @TypeConverter
    fun StringToTmdbMovie(value: String): TmdbMovie? {
        return filmJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbMovieToString(film: TmdbMovie): String {
        return filmJsonadapter.toJson(film)
    }
}