package com.ahmaddudayef.jetpackcomposeplayground.artspaceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmaddudayef.jetpackcomposeplayground.R
import com.ahmaddudayef.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
import kotlinx.coroutines.delay

@Composable
fun ArtSpaceApp() {
    // Artwork list
    val artworks = listOf(
        Artwork(R.drawable.bridge_image, "Sailing Under the Bridge", "Kat Kuan", 2017),
        Artwork(R.drawable.mountain_image, "Mountains of Madness", "Paul Colson", 2019),
        Artwork(R.drawable.forest_image, "Whispers in the Forest", "Anna Kwon", 2021)
    )

    // Dynamic state for current artwork index
    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    // Tooltip visibility state
    var showTooltip by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                // Detect horizontal drag gestures (swipes)
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume() // Consume gesture
                    if (dragAmount > 0) {
                        // Swiped to the right, show previous artwork
                        currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
                    } else {
                        // Swiped to the left, show next artwork
                        currentIndex = if (currentIndex == artworks.size - 1) 0 else currentIndex + 1
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Section 1: Artwork display
        ArtworkWall(artwork = currentArtwork)

        // Section 2: Artwork descriptor (Title, artist, year)
        ArtworkDescriptor(artwork = currentArtwork)

        // Section 3: Control buttons (Next, Previous)
        DisplayController(
            onPreviousClick = {
                currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
            },
            onNextClick = {
                currentIndex = if (currentIndex == artworks.size - 1) 0 else currentIndex + 1
            },
            onLongPress = {
                // Show tooltip when long-pressed
                showTooltip = true
            }
        )

        // Tooltip implementation
        if (showTooltip) {
            TooltipText("Hold for more info!") {
                showTooltip = false
            }
        }
    }
}

@Composable
fun ArtworkWall(artwork: Artwork) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(artwork.imageResId),
            contentDescription = null,  // No description needed
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp)
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
fun DisplayController(onPreviousClick: () -> Unit, onNextClick: () -> Unit, onLongPress: () -> Unit) {
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
            modifier = Modifier
                .padding(8.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            onLongPress()
                        }
                    )
                }
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun TooltipText(text: String, onDismiss: () -> Unit) {
    // Show tooltip for a few seconds and then dismiss
    LaunchedEffect(Unit) {
        delay(2000)
        onDismiss()
    }

    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(text = text)
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