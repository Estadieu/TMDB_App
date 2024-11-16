package com.example.my_application1.ui.serie

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.theme.PurpleGrey40
import com.example.my_application1.ui.theme.PurpleGrey80

@Composable
fun SeriesSelected(
    navController: NavController,
    viewModel: MainViewModel,
    id: Int,
    windowClass: WindowSizeClass
) {
    val seriesSelected = viewModel.series_select.collectAsState()
    val seriesActeurs by viewModel.seriesCast.collectAsState()

    // Lancement
    LaunchedEffect(id) {
        viewModel.selectedSeries(id)
        viewModel.getActeurSeries(id)
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
            // Titre de la série
            item {
                Text(
                    text = seriesSelected.value.name ?: "Unknown Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSizeTitle,
                    fontFamily = FontFamily.Serif,
                    color = PurpleGrey40,
                    textAlign = TextAlign.Center
                )
            }

            // Image et grille des détails
            item {
                if (isCompactScreen) {
                    // Affichage en colonne pour écrans compacts
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/" + seriesSelected.value.backdrop_path,
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
                                "LANGUE: ${seriesSelected.value.original_language}",
                                "POPULARITÉ: ${seriesSelected.value.popularity}",
                                "VOTE: ${seriesSelected.value.vote_count}",
                                "SAISONS: ${seriesSelected.value.number_of_seasons}"
                            )
                            items(detailsList) { detail ->
                                SerieDetailRow(detail = detail)
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
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w300/" + seriesSelected.value.backdrop_path,
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .aspectRatio(4f / 3f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(PurpleGrey40)
                                .border(5.dp, PurpleGrey40, RoundedCornerShape(10.dp))
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            val detailsList = listOf(
                                "LANGUE: ${seriesSelected.value.original_language}",
                                "POPULARITÉ: ${seriesSelected.value.popularity}",
                                "VOTE: ${seriesSelected.value.vote_count}",
                                "SAISONS: ${seriesSelected.value.number_of_seasons}"
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
                        text = seriesSelected.value.overview ?: "No synopsis available.",
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
                    items(seriesActeurs) { actor ->
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
fun SerieDetailRow(detail: String) {
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
