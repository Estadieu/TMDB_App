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
fun FilmSelected(navController: NavController, viewModel: MainViewModel, id: String, windowClass: WindowSizeClass) {
    val filmSelected = viewModel.movies_select.collectAsState()

    LaunchedEffect(true) {
        viewModel.selectedMovies(id)
    }

    if (LocalConfiguration.current.screenWidthDp < 600) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = filmSelected.value.original_title,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                fontFamily = FontFamily.Default
            )

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/" + filmSelected.value.backdrop_path,
                contentDescription = null,
            )

            Text(
                text = filmSelected.value.release_date,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

            Text(
                text = "Language: " + filmSelected.value.original_language,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            Text(
                text = "Popularity: " + filmSelected.value.popularity,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            Text(
                text = "Vote: " + filmSelected.value.vote_count,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Synopsis: " + filmSelected.value.overview,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Justify
            )
        }
    } else {
        Row(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = filmSelected.value.original_title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.padding(18.dp))

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + filmSelected.value.backdrop_path,
                    contentDescription = null,
                )

                Text(
                    text = filmSelected.value.release_date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Language: " + filmSelected.value.original_language,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Popularity: " + filmSelected.value.popularity,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Vote: " + filmSelected.value.vote_count,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
            }

            Spacer(modifier = Modifier.padding(26.dp))

            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Synopsis: " + filmSelected.value.overview,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
