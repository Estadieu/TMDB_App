package com.example.my_application1.ui.Film
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.window.core.layout.WindowSizeClass
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.theme.PurpleGrey40
import com.example.my_application1.ui.theme.PurpleGrey80
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.shadow


@Composable
fun FilmSelected(
    navController: NavController,
    viewModel: MainViewModel,
    id: Int,
    windowClass: WindowSizeClass
) {
    val filmSelected = viewModel.movies_select.collectAsState()
    val movieActeurs by viewModel.movieCast.collectAsState()

    // Lancement
    LaunchedEffect(id) {
        viewModel.selectedMovies(id)
        viewModel.getActeurMovie(id)
    }

    val isCompactScreen = LocalConfiguration.current.screenWidthDp < 600
    val contentPadding = if (isCompactScreen) 16.dp else 32.dp
    val fontSizeTitle = if (isCompactScreen) 24.sp else 32.sp
    val gridColumns = if (isCompactScreen) 2 else 4

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey80)
            .padding(contentPadding)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Titre du film
            item {
                Text(
                    text = filmSelected.value.original_title,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSizeTitle,
                    fontFamily = FontFamily.Serif,
                    color = PurpleGrey40,
                    textAlign = TextAlign.Center
                )
            }

            // Image et grille des détails (adaptatif)
            item {
                if (isCompactScreen) {
                    // Affichage en colonne pour écrans compacts
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/" + filmSelected.value.backdrop_path,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(8.dp))
                                .shadow(elevation = 12.dp)
                        )

                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(gridColumns),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            val detailsList = listOf(
                                "LANGUE: ${filmSelected.value.original_language}",
                                "POPULARITE: ${filmSelected.value.popularity}",
                                "VOTE: ${filmSelected.value.vote_count}",
                                "BUDGET: ${filmSelected.value.budget}"
                            )
                            items(detailsList) { detail ->
                                FilmDetailRow(detail = detail)
                            }
                        }
                    }
                } else {
                    // Affichage côte à côte pour écrans larges
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        // Récupération des dimensions de l'écran
                        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                        val rowWidth = screenWidth * 1.2f // La Row occupe
                        val imageWidth = rowWidth * 0.6f // L'image occupe 60% de la largeur de la Row
                        val detailsWidth = rowWidth * 0.3f // Les détails occupent 30% de la largeur de la Row

                        // Image avec bordures arrondies et taille dynamique
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w300/" + filmSelected.value.backdrop_path,
                            contentDescription = null,
                            modifier = Modifier
                                .width(imageWidth/1.9f)
                                .aspectRatio(4f / 3f) // Aspect ratio constant
                                .clip(RoundedCornerShape(10.dp))
                                .background(PurpleGrey40)
                                .border(5.dp, PurpleGrey40, RoundedCornerShape(10.dp))
                        )

                        // Section des détails du film
                        Column(
                            modifier = Modifier
                                .width(detailsWidth)
                                .padding(0.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            val detailsList = listOf(
                                "LANGUE: ${filmSelected.value.original_language}",
                                "POPULARITÉ: ${filmSelected.value.popularity}",
                                "VOTE: ${filmSelected.value.vote_count}",
                                "BUDGET: ${filmSelected.value.budget}"
                            )
                            detailsList.forEach { detail ->
                                Text(
                                    text = detail,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PurpleGrey80,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(PurpleGrey40)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Synopsis
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Synopsis",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = PurpleGrey40,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = filmSelected.value.overview,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        color = PurpleGrey40,
                        textAlign = TextAlign.Justify
                    )
                }
            }

            // Acteurs principaux
            item {
                Text(
                    text = "Acteurs principaux",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = PurpleGrey40,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(movieActeurs) { actor ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(120.dp)
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w200${actor.profile_path}",
                                contentDescription = actor.name,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(40.dp))
                                    .background(PurpleGrey40)
                            )
                            Text(
                                text = actor.name,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp),
                                color = PurpleGrey40
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilmDetailRow(detail: String) {
    Text(
        text = detail,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = PurpleGrey40,
        modifier = Modifier

            .fillMaxWidth()
            .clip(RoundedCornerShape(3.dp))
    )
}
