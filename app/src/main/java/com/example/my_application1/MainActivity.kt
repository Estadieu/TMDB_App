package com.example.my_application1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_application1.ui.ActorsScreen
import com.example.my_application1.ui.SeriesScreen
import com.example.my_application1.ui.FilmsScreen
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.my_application1.ui.MainViewModel
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.my_application1.ui.FilmSelected
import kotlinx.serialization.json.Json

//import com.example.my_application1.ui.ResponsiveHomeScreen

//Pour les destination
@Serializable
class FilmsScreendest
@Serializable
class SeriesScreendest
@Serializable
class ActorsScreendest

@Serializable
class FilmDetailsDest(val movieId: String)
@Serializable
class SeriesDetailsDest(val seriesId: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
            //Pour Afficher le profil
            // taille de la fenêtre
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            ResponsiveHomeScreen(windowClass = windowSizeClass)
            */
            val navController = rememberNavController()
            MyApp(navController)
        }
    }
}


@Composable
fun MyApp(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntry?.destination
    val modelFilm: MainViewModel = viewModel()
    val modelSeries: MainViewModel = viewModel()
    val modelActors: MainViewModel = viewModel()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Films") },
                        label = { Text("Films") },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute<FilmsScreendest>()
                        } == true,
                        onClick = { navController.navigate(FilmsScreendest()) }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Star, contentDescription = "Séries") },
                        label = { Text("Séries") },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute<SeriesScreendest>()
                        } == true,
                        onClick = { navController.navigate(SeriesScreendest()) }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Acteurs") },
                        label = { Text("Acteurs") },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute<ActorsScreendest>()
                        } == true,
                        onClick = { navController.navigate(ActorsScreendest()) }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = FilmsScreendest(),
                Modifier.padding(innerPadding)
            ) {
                composable<FilmsScreendest> { FilmsScreen(navController, modelFilm, windowSizeClass) }
                composable<SeriesScreendest> { SeriesScreen(navController, modelSeries, windowSizeClass) }
                composable<ActorsScreendest> { ActorsScreen(navController, modelActors, windowSizeClass) }
                composable("film_details/{movieDetails}") { backStackEntry ->
                    val movieDetailsJson = backStackEntry.arguments?.getString("movieDetails")
                    movieDetailsJson?.let {
                        val movieDetails = Json.decodeFromString<FilmDetailsDest>(it)
                        FilmSelected(navController, modelFilm, movieDetails.movieId, windowSizeClass)
                    }
                }

            }
        }
    } else {
        Scaffold(
            content = { innerPadding ->
                Row(Modifier.padding(innerPadding)) {
                    NavigationRail {
                        NavigationRailItem(
                            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Films") },
                            label = { Text("Films") },
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute<FilmsScreendest>()
                            } == true,
                            onClick = { navController.navigate(FilmsScreendest()) }
                        )
                        NavigationRailItem(
                            icon = { Icon(Icons.Filled.Star, contentDescription = "Séries") },
                            label = { Text("Séries") },
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute<SeriesScreendest>()
                            } == true,
                            onClick = { navController.navigate(SeriesScreendest()) }
                        )
                        NavigationRailItem(
                            icon = { Icon(Icons.Filled.Person, contentDescription = "Acteurs") },
                            label = { Text("Acteurs") },
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute<ActorsScreendest>()
                            } == true,
                            onClick = { navController.navigate(ActorsScreendest()) }
                        )
                    }
                    NavHost(
                        navController = navController,
                        startDestination = FilmsScreendest(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<FilmsScreendest> {
                            FilmsScreen(
                                navController,
                                modelFilm,
                                windowSizeClass
                            )
                        }
                        composable<SeriesScreendest> {
                            SeriesScreen(
                                navController,
                                modelSeries,
                                windowSizeClass
                            )
                        }
                        composable<ActorsScreendest> {
                            ActorsScreen(
                                navController,
                                modelActors,
                                windowSizeClass
                            )
                        }
                        composable("film_details/{movieDetails}") { backStackEntry ->
                            val movieDetailsJson = backStackEntry.arguments?.getString("movieDetails")
                            movieDetailsJson?.let {
                                val movieDetails = Json.decodeFromString<FilmDetailsDest>(it)
                                FilmSelected(navController, modelFilm, movieDetails.movieId, windowSizeClass)
                            }
                        }
                    }
                }
            }
        )
    }
}