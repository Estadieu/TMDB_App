package com.example.my_application1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import com.example.my_application1.ui.theme.PurpleGrey40


@Composable
fun ActorsScreen(navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    // Récupérer l'état des acteurs à partir du ViewModel
    val actorsState = viewModel.actors.collectAsState()
    val actors = actorsState.value
    // Etat pour stocker le mot-clé
    var searchQuery by remember { mutableStateOf("") }

    // Déclencher la recherche (Si pas de mot clé -> acteurs populaires, voir code searchActors)
    LaunchedEffect(Unit) {
        viewModel.searchActors("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barre de recherche
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                viewModel.searchActors(query) // Appel de la fonction de recherche avec le mot-clé
            },
            label = { Text("Rechercher un acteur...") },
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

        // Affichage de la grille des acteurs
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(actors) { actor ->
                ActorItem(actor = actor, windowClass = windowClass)
            }
        }
    }
}

@Composable
fun ActorItem(actor: Actor, windowClass: WindowSizeClass) {
    // Adapte la taille des éléments en fonction de la taille de l'écran avec des proportions
    val imageHeightFraction = when (windowClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 0.2f // 20% de la hauteur disponible
        WindowHeightSizeClass.MEDIUM -> 0.25f // 25% de la hauteur disponible
        WindowHeightSizeClass.EXPANDED -> 0.3f // 30% de la hauteur disponible
        else -> 0.25f
    }

    val textHeightFraction = 0.1f // Le texte prend 10% de la hauteur

    // Couleur
    val backgroundColor = PurpleGrey40 // Violet par défaut

    // Utilisation d'une Card pour encapsuler le contenu d'un acteur
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
            // Image de l'acteur ajustée proportionnellement à la hauteur de l'écran
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400${actor.profile_path}",
                contentDescription = actor.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageHeightFraction) // Hauteur proportionnelle à l'écran
            )

            // Nom de l'acteur
            Text(
                text = actor.name,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction) // Hauteur proportionnelle pour le texte
            )

            // Date de naissance ou autre information pertinente
            Text(
                text = actor.known_for_department, // exemple de champ pertinent
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
        }
    }
}