package com.example.my_application1.ui.Film
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
import coil.compose.AsyncImage
import androidx.window.core.layout.WindowSizeClass
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.theme.PurpleGrey40
import com.example.my_application1.ui.theme.PurpleGrey80
import androidx.compose.foundation.lazy.grid.items


@Composable
fun FilmSelected(navController: NavController, viewModel: MainViewModel, id: String, windowClass: WindowSizeClass) {
    val filmSelected = viewModel.movies_select.collectAsState()

    LaunchedEffect(true) {
        viewModel.selectedMovies(id)
    }

    // Couleur de fond
    val backgroundColor = PurpleGrey80

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {

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
                    text = filmSelected.value.original_title,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompactScreen) 24.sp else 32.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.DarkGray
                )

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + filmSelected.value.backdrop_path,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    text = filmSelected.value.release_date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Gray
                )
            }

            // Utilisation de LazyHorizontalGrid pour afficher les détails en grille
            item {
                val detailsList = listOf(
                    "Language: ${filmSelected.value.original_language}",
                    "Popularity: ${filmSelected.value.popularity}",
                    "Vote: ${filmSelected.value.vote_count}",
                    "Budget: ${filmSelected.value.budget}"
                )

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(detailsList) { detail ->
                        FilmDetailRow(detail = detail)
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
                    text = filmSelected.value.overview,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun FilmDetailRow(detail: String) {
    Text(
        text = detail,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(PurpleGrey40)
            .padding(8.dp)
    )
}