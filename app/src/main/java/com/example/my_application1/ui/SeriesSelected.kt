package com.example.my_application1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.window.core.layout.WindowSizeClass

@Composable
fun SeriesSelected(navController: NavController, viewModel: MainViewModel, id: String, windowClass: WindowSizeClass) {
    val seriesSelected = viewModel.series_select.collectAsState()

    LaunchedEffect(true) {
        viewModel.selectedSeries(id)
    }

    if (LocalConfiguration.current.screenWidthDp < 600) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Vérification si le nom n'est pas null
            seriesSelected.value.name?.let { name ->
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default
                )
            }

            // Vérification si le chemin d'image du backdrop n'est pas null
            seriesSelected.value.backdrop_path?.let { backdropPath ->
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/$backdropPath",
                    contentDescription = null,
                )
            }

            // Vérification si la date de première diffusion n'est pas null
            seriesSelected.value.first_air_date?.let { firstAirDate ->
                Text(
                    text = firstAirDate,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            }

            // Vérification si la langue originale n'est pas null
            seriesSelected.value.original_language?.let { originalLanguage ->
                Text(
                    text = "Language: $originalLanguage",
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
            }

            // Vérification si la popularité n'est pas null
            Text(
                text = "Popularity: ${seriesSelected.value.popularity}",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            // Vérification si le nombre de votes n'est pas null
            Text(
                text = "Vote: ${seriesSelected.value.vote_count}",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.padding(10.dp))

            // Vérification si le synopsis (overview) n'est pas null
            seriesSelected.value.overview?.let { overview ->
                Text(
                    text = "Synopsis: $overview",
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Justify
                )
            }

            // Vérification si la liste des créateurs n'est pas vide
            if (seriesSelected.value.created_by.isNotEmpty()) {
                Text(
                    text = "Created by: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.padding(top = 16.dp)
                )

                seriesSelected.value.created_by.forEach { creator ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        // Vérification si le profil du créateur est renseigné
                        creator.profile_path?.let { profilePath ->
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w200/$profilePath",
                                contentDescription = creator.name,
                                modifier = Modifier.size(50.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Affichage du nom du créateur s'il n'est pas null
                        Text(
                            text = creator.name ?: "Unknown",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }
        }
    } else {
        // Version pour les grands écrans (large)
        Row(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                // Vérification si le nom n'est pas null
                seriesSelected.value.name?.let { name ->
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default
                    )
                }

                Spacer(modifier = Modifier.padding(18.dp))

                // Vérification si le chemin d'image du backdrop n'est pas null
                seriesSelected.value.backdrop_path?.let { backdropPath ->
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/$backdropPath",
                        contentDescription = null,
                    )
                }

                // Vérification si la date de première diffusion n'est pas null
                seriesSelected.value.first_air_date?.let { firstAirDate ->
                    Text(
                        text = firstAirDate,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )
                }

                // Vérification si la langue originale n'est pas null
                seriesSelected.value.original_language?.let { originalLanguage ->
                    Text(
                        text = "Language: $originalLanguage",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )
                }

                Text(
                    text = "Popularity: ${seriesSelected.value.popularity}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Vote: ${seriesSelected.value.vote_count}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                // Vérification si la liste des créateurs n'est pas vide
                if (seriesSelected.value.created_by.isNotEmpty()) {
                    Text(
                        text = "Created by: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    seriesSelected.value.created_by.forEach { creator ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            creator.profile_path?.let { profilePath ->
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w200/$profilePath",
                                    contentDescription = creator.name,
                                    modifier = Modifier.size(50.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = creator.name ?: "Unknown",
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(26.dp))

            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                seriesSelected.value.overview?.let { overview ->
                    Text(
                        text = "Synopsis: $overview",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}
