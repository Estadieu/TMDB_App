package com.example.my_application1.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.core.layout.WindowSizeClass
import coil.compose.AsyncImage
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.Model.Result
import com.example.my_application1.ui.theme.PurpleGrey40

@Composable
fun Collection(navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    val collectionsState = viewModel.collections.collectAsState()
    val collections = collectionsState.value

    // Charger les collections au lancement
    LaunchedEffect(Unit) {
        viewModel.searchCollections("horror")
    }

    // Conteneur principal
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Permet de s'adapter à la taille de l'écran
        val columns = when (windowClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> 2
            WindowWidthSizeClass.MEDIUM -> 3
            WindowWidthSizeClass.EXPANDED -> 4
            else -> 2
        }

        // Grille pour afficher les collections
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

        ) {
            items(collections) { collection ->
                CollectionItem(collection = collection, windowClass = windowClass) {
                    // Navigation lors du clic sur une collection
                    navController.navigate("CollectionDetails/${collection.id}")
                }
            }
        }
    }
}

@Composable
fun CollectionItem(collection: Result, windowClass: WindowSizeClass, onClick: () -> Unit) {
    val imageHeightFraction = when (windowClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 0.2f
        WindowHeightSizeClass.MEDIUM -> 0.25f
        WindowHeightSizeClass.EXPANDED -> 0.3f
        else -> 0.25f
    }

    val textHeightFraction = 0.1f
    val backgroundColor = PurpleGrey40

    // Carte pour chaque collection
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400${collection.poster_path}",
                contentDescription = collection.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageHeightFraction)
            )
            Text(
                text = collection.name,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
            Text(
                text = collection.overview ?: "No description",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
        }
    }
}
