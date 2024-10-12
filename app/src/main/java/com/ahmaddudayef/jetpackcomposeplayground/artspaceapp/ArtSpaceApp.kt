package com.ahmaddudayef.jetpackcomposeplayground.artspaceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmaddudayef.jetpackcomposeplayground.R
import com.ahmaddudayef.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun ArtSpaceApp() {
    // You can keep a list of artworks and state to control the current index
    val artworks = listOf(
        Artwork(R.drawable.bridge_image, "Sailing Under the Bridge", "Kat Kuan", 2017),
        // Add more artworks if necessary
    )

    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Section 1: Artwork wall (Image)
        ArtworkWall(artwork = currentArtwork)

        // Section 2: Artwork descriptor (Title, artist, year)
        ArtworkDescriptor(artwork = currentArtwork)

        // Section 3: Display controller (Buttons)
        DisplayController(
            onPreviousClick = {
                if (currentIndex > 0) currentIndex--
            },
            onNextClick = {
                if (currentIndex < artworks.size - 1) currentIndex++
            }
        )
    }
}

@Composable
fun ArtworkWall(artwork: Artwork) {
    Surface(
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painter = painterResource(artwork.imageResId),
            contentDescription = null,  // No description needed, purely decorative
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(artwork: Artwork) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = artwork.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "by ${artwork.artist}",
            fontSize = 18.sp,
            color = Color.Gray,
        )
        Text(
            text = "(${artwork.year})",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun DisplayController(onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

data class Artwork(
    val imageResId: Int,
    val title: String,
    val artist: String,
    val year: Int
)

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    JetpackComposePlaygroundTheme {
        ArtSpaceApp()
    }
}