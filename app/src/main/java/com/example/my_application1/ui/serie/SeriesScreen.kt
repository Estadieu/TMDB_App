package com.example.my_application1.ui.serie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import com.example.my_application1.FilmDetailsDest
import com.example.my_application1.SeriesDetailsDest
import com.example.my_application1.ui.Model.MainViewModel
import com.example.my_application1.ui.Model.Serie
import com.example.my_application1.ui.theme.PurpleGrey40
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SeriesScreen(navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    val seriesState = viewModel.series.collectAsState()
    val series = seriesState.value
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.searchSeries("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                viewModel.searchSeries(query)
            },
            label = { Text("Rechercher une Serie...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        val columns = when (windowClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> 2
            WindowWidthSizeClass.MEDIUM -> 3
            WindowWidthSizeClass.EXPANDED -> 4
            else -> 2
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(series) { serie ->
                SerieItem(serie = serie, windowClass = windowClass) {
                    navController.navigate(SeriesDetailsDest(serie.id))
                }
            }
        }
    }
}

@Composable
fun SerieItem(serie: Serie, windowClass: WindowSizeClass, onClick: () -> Unit) {
    val imageHeightFraction = when (windowClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 0.2f
        WindowHeightSizeClass.MEDIUM -> 0.25f
        WindowHeightSizeClass.EXPANDED -> 0.3f
        else -> 0.25f
    }

    val textHeightFraction = 0.1f
    val backgroundColor = PurpleGrey40

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
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
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400${serie.poster_path}",
                contentDescription = serie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageHeightFraction)
            )

            Text(
                text = serie.original_name,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )

            Text(
                text = serie.first_air_date,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(textHeightFraction)
            )
        }
    }
}
