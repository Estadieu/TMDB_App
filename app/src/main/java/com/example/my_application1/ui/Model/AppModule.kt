package com.example.my_application1.ui.Model

import FakeTmdbApi
import android.content.Context
import androidx.room.Room
import com.example.my_application1.ui.Api.Tmdbapi
import com.example.my_application1.ui.Model.Repository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class FakeApi
@Qualifier
annotation class RealApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @RealApi
    @Singleton
    @Provides
    fun provideTmdbApi() : Tmdbapi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Tmdbapi::class.java)

    @FakeApi
    @Singleton
    @Provides
    fun provideFakeTmdbApi() : Tmdbapi { return FakeTmdbApi() }

    @Singleton
    @Provides
    fun provideRepository(@FakeApi api: Tmdbapi, db: FilmDao) = Repository(api, db)

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context, converters : Converters)
            : FilmDao =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build().filmDao()

    @Singleton
    @Provides
    fun providerConverters() : Converters {
        val moshi = Moshi.Builder().build()
// remplacer ici Converters, par le nom que vous avez donné à votre classe
// convertisseur de types.
        return Converters(moshi)
    }
}