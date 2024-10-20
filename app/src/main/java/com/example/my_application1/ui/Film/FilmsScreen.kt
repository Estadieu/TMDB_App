package com.example.my_application1.ui.Film

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.core.layout.WindowSizeClass
import com.example.my_application1.FilmDetailsDest
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.Model.TmdbMovie
import com.example.my_application1.ui.theme.PurpleGrey40
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun FilmsScreen(navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    val moviesState = viewModel.movies.collectAsState()
    val movies = moviesState.value
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.searchMovies("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barre de recherche
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                viewModel.searchMovies(query)
            },
            label = { Text("Rechercher un film...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        val columns = when (windowClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> 2
            WindowWidthSizeClass.MEDIUM -> 3
            WindowWidthSizeClass.EXPANDED -> 4
            else -> 2
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, windowClass = windowClass) {
                    // Sérialiser l'objet FilmDetailsDest en JSON
                    val movieDetailsJson = Json.encodeToString(FilmDetailsDest(movie.id.toString()))
                    // Naviguer en passant le JSON dans l'argument de la route
                    navController.navigate("film_details/$movieDetailsJson")
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: TmdbMovie, windowClass: WindowSizeClass, onClick: () -> Unit) {
    val imageHeightFraction = when (windowClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 0.2f
        WindowHeightSizeClass.MEDIUM -> 0.25f
        WindowHeightSizeClass.EXPANDED -> 0.3f
        else -> 0.25f
    }

    val textHeightFraction = 0.1f
    val backgroundColor = PurpleGrey40

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400${movie.poster_path}",
                contentDescription = movie.original_title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageHeightFraction)
            )
            Text(
                text = movie.original_title,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
            Text(
                text = movie.release_date,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
        }
    }
}
