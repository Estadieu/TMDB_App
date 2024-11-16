package com.example.my_application1.ui.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_application1.R
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.core.layout.WindowSizeClass
import com.example.my_application1.FilmsScreendest

@Composable
fun ResponsiveHomeScreen(navController: NavController, windowClass: WindowSizeClass , onStartClicked: () -> Unit) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ProfileImage(maxWidth = windowClass.windowWidthSizeClass)
                Spacer(modifier = Modifier.height(20.dp))
                ProfileInfo(maxWidth = windowClass.windowWidthSizeClass)
                Spacer(modifier = Modifier.height(20.dp))
                ContactInfo()
                Spacer(modifier = Modifier.height(20.dp))
                ActionButton{
                    navController.navigate(FilmsScreendest()) // Redirige vers FilmsScreen
                }
            }
        }
        else -> { // For medium and expanded screens
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileImage(maxWidth = windowClass.windowWidthSizeClass)
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileInfo(maxWidth = windowClass.windowWidthSizeClass)
                    Spacer(modifier = Modifier.height(20.dp))
                    ContactInfo()
                    Spacer(modifier = Modifier.height(20.dp))
                    ActionButton{
                        navController.navigate(FilmsScreendest()) // Redirige vers FilmsScreen
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImage(maxWidth: WindowWidthSizeClass) {
    val imageSize = calculateResponsiveSize(maxWidth, 150.dp)
    Image(
        painter = painterResource(id = R.drawable._624438524294),
        contentDescription = "Image de profil",
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProfileInfo(maxWidth: WindowWidthSizeClass) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Jean Estadieu",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = calculateResponsiveTextSize(maxWidth, 24.sp)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Étudiant ingénieur ISIS",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = calculateResponsiveTextSize(maxWidth, 16.sp)
            )
        )
    }
}

@Composable
fun ContactInfo() {
    val context = LocalContext.current
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mail),
                contentDescription = "Icone Email",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "jean.estadieu@orange.fr")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.linkedin.com/in/jean-estadieu-09869419b"))
                    context.startActivity(intent)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.link),
                contentDescription = "Icone LinkedIn",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Jean Estadieu",
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun ActionButton(onStartClicked: () -> Unit) {
    Button(onClick = {
        onStartClicked()
    })
    {
        Text(text = "Démarrer")
    }
}

@Composable
fun calculateResponsiveSize(windowWidthSizeClass: WindowWidthSizeClass, baseSize: Dp): Dp {
    return when (windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> baseSize * 0.8f
        WindowWidthSizeClass.MEDIUM -> baseSize * 1.0f
        WindowWidthSizeClass.EXPANDED -> baseSize * 1.2f
        else -> baseSize
    }
}

@Composable
fun calculateResponsiveTextSize(windowWidthSizeClass: WindowWidthSizeClass, baseSize: TextUnit): TextUnit {
    return when (windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> baseSize * 0.8f
        WindowWidthSizeClass.MEDIUM -> baseSize * 1.0f
        WindowWidthSizeClass.EXPANDED -> baseSize * 1.2f
        else -> baseSize
    }
}


