package com.example.my_application1.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.my_application1.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
/*
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val imageSize = calculateResponsiveSize(screenWidth, 150.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Image de profil
            Image(
                painter = painterResource(id = R.drawable._624438524294),
                contentDescription = "Image de profil",
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Texte de présentation
            Text(
                text = "Jean Estadieu",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = calculateResponsiveTextSize(screenWidth, 24.sp)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texte secondaire
            Text(
                text = "Étudiant ingénieur ISIS",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = calculateResponsiveTextSize(screenWidth, 16.sp)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Adresse e-mail
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

            // Lien LinkedIn
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

}
*/