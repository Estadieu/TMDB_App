package com.example.my_application1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.padding

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
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
import com.example.my_application1.ui.ResponsiveHomeScreen


@Serializable
class FilmsScreendest

@Serializable
class SeriesScreendest

@Serializable
class ActorsScreendest



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
    val model : MainViewModel = viewModel()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
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
                    } ==true,
                    onClick = { navController.navigate(SeriesScreendest()) }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Acteurs") },
                    label = { Text("Séries") },
                    selected = currentDestination?.hierarchy?.any {
                        it.hasRoute<ActorsScreendest>()
                    } ==true,
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
            composable<FilmsScreendest> { FilmsScreen(navController,model,windowSizeClass) }
            composable<SeriesScreendest> { SeriesScreen(navController) }
            composable<ActorsScreendest> { ActorsScreen(navController) }
        }
    }
}