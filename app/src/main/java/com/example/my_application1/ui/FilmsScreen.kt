package com.example.my_application1.ui

import androidx.compose.foundation.background
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

    // Vérifier si la liste est vide et déclencher la recherche
    if (movies.isEmpty()) {
        viewModel.searchMoviesPopular()
    }

    // Calculer le nombre de colonnes en fonction de la taille de l'écran
    val columns = when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 2  // Deux colonnes -> pour petits écrans
        WindowWidthSizeClass.MEDIUM -> 3   // Trois colonnes -> pour écrans moyens
        WindowWidthSizeClass.EXPANDED -> 4 // Quatre colonnes -> pour grands écrans
        else -> 2
    }

    // Affichage avec LazyVerticalGrid pour une meilleure gestion des colonnes
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

    // Couleur de fond violette par défaut pour la carte
    val backgroundColor = Color(0xFF6200EE) // Violet par défaut

    // Utilisation d'une Card pour encapsuler le contenu avec une couleur de fond violette
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp), // Utilisation de cardElevation pour l'élévation
        colors = CardDefaults.cardColors(containerColor = backgroundColor) // Définir la couleur de fond violette ici
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
                color = Color.White,  // Le texte en blanc pour contraster avec le fond violet
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction) // Hauteur proportionnelle pour le texte
            )

            // Date de sortie du film
            Text(
                text = movie.release_date,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f), // Le texte en blanc pour contraster avec le fond violet
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction) // Hauteur proportionnelle pour la date
            )
        }
    }
}
