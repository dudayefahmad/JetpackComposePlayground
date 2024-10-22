package com.ahmaddudayef.jetpackcomposeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.ahmaddudayef.jetpackcomposeplayground.topic.TopicGridApp
import com.ahmaddudayef.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
import com.ahmaddudayef.jetpackcomposeplayground.woof.WoofApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    WoofApp()
                }
            }
        }
    }
}