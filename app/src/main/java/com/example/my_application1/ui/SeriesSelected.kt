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
            Text(
                text = seriesSelected.value.name,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                fontFamily = FontFamily.Default
            )

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/" + seriesSelected.value.backdrop_path,
                contentDescription = null,
            )

            Text(
                text = seriesSelected.value.first_air_date,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

            Text(
                text = "Language: " + seriesSelected.value.original_language,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            Text(
                text = "Popularity: " + seriesSelected.value.popularity,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            Text(
                text = "Vote: " + seriesSelected.value.vote_count,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Synopsis: " + seriesSelected.value.overview,
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
                    text = seriesSelected.value.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.padding(18.dp))

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + seriesSelected.value.backdrop_path,
                    contentDescription = null,
                )

                Text(
                    text = seriesSelected.value.first_air_date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Language: " + seriesSelected.value.original_language,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Popularity: " + seriesSelected.value.popularity,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Vote: " + seriesSelected.value.vote_count,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
            }

            Spacer(modifier = Modifier.padding(26.dp))

            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Synopsis: " + seriesSelected.value.overview,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}