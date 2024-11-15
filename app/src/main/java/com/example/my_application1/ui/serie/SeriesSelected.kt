package com.example.my_application1.ui.serie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.theme.PurpleGrey40
import com.example.my_application1.ui.theme.PurpleGrey80
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext

@Composable
fun SeriesSelected(navController: NavController, viewModel: MainViewModel, id: Int, windowClass: WindowSizeClass) {
    val seriesSelected = viewModel.series_select.collectAsState()
    val seriesActeurs by viewModel.seriesCast.collectAsState()

    LaunchedEffect(id) {
        viewModel.selectedSeries(id)
        viewModel.getActeurSeries(id)
    }

    val backgroundColor = PurpleGrey80
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        val isCompactScreen = LocalConfiguration.current.screenWidthDp < 600
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = seriesSelected.value.name ?: "Unknown Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompactScreen) 24.sp else 32.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(10.dp))

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + seriesSelected.value.backdrop_path,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shadow(elevation = 20.dp)
                )

                Text(
                    text = seriesSelected.value.first_air_date ?: "Unknown Date",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Gray
                )
            }

            item {
                val detailsList = listOf(
                    "Language: ${seriesSelected.value.original_language}",
                    "Popularity: ${seriesSelected.value.popularity}",
                    "Vote: ${seriesSelected.value.vote_count}",
                    "Seasons: ${seriesSelected.value.number_of_seasons}",


                    )

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(detailsList) { detail ->
                        SerieDetailRow(detail = detail)
                    }
                }
            }

            item {
                Text(
                    text = "Synopsis:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.DarkGray
                )

                Text(
                    text = seriesSelected.value.overview ?: "No synopsis available.",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    fontSize = 20.sp,
                    text = "Acteurs principaux de la série: ",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                seriesActeurs.take(6).chunked(2).forEach { actorPair ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        actorPair.forEach { actor ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f).padding(end = 8.dp)
                            ) {
                                val profileUrl =
                                    "https://image.tmdb.org/t/p/w200${actor.profile_path}"
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(profileUrl)
                                        .transformations(CircleCropTransformation())
                                        .build(),
                                    contentDescription = actor.name,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(end = 8.dp)
                                )

                                Text(
                                    text = actor.name,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                        }
                        if (actorPair.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun SerieDetailRow(detail: String) {
    Text(
        text = detail,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(PurpleGrey40)
            .padding(6.dp)
    )
}
