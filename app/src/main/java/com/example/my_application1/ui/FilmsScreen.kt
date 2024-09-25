package com.example.my_application1.ui

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

@Composable
fun FilmsScreen(navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    // Récupérer l'état des films à partir du ViewModel
    val moviesState = viewModel.movies.collectAsState()
    val movies = moviesState.value
    // Etat pour stocker le mot-clé
    var searchQuery by remember { mutableStateOf("") }

    //Déclencher la recherche (Si pas de mot clé -> film + Pop, voir code searchMovies)
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
                viewModel.searchMovies(query) // Appel de la fonction de recherche avec le mot-clé
            },
            label = { Text("Rechercher un film...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Calculer le nombre de colonnes en fonction de la taille de l'écran
        val columns = when (windowClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> 2  // Deux colonnes pour les petits écrans
            WindowWidthSizeClass.MEDIUM -> 3   // Trois colonnes pour les écrans moyens
            WindowWidthSizeClass.EXPANDED -> 4 // Quatre colonnes pour les grands écrans
            else -> 2
        }

        // Affichage de la grille des films
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, windowClass = windowClass)
            }
        }
    }
}

@Composable
fun MovieItem(movie: TmdbMovie, windowClass: WindowSizeClass) {
    // Adapte la taille des éléments en fonction de la taille de l'écran avec des proportions
    val imageHeightFraction = when (windowClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 0.2f // 20% de la hauteur disponible
        WindowHeightSizeClass.MEDIUM -> 0.25f // 25% de la hauteur disponible
        WindowHeightSizeClass.EXPANDED -> 0.3f // 30% de la hauteur disponible
        else -> 0.25f
    }

    val textHeightFraction = 0.1f // Le texte prend 10% de la hauteur

    // Couleur
    val backgroundColor = Color(0xFF6200EE) // Violet par défaut

    // Utilisation d'une Card pour encapsuler le contenu d'un film
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
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
            // Image du film ajustée proportionnellement à la hauteur de l'écran
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400${movie.poster_path}",
                contentDescription = movie.original_title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageHeightFraction) // Hauteur proportionnelle à l'écran
            )

            // Titre du film
            Text(
                text = movie.original_title,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction) // Hauteur proportionnelle pour le texte
            )

            // Date de sortie du film
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
