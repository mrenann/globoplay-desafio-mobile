package com.mrenann.globoplay.mediaDetailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.mrenann.globoplay.R
import com.mrenann.globoplay.ui.theme.Background

@Composable
fun BackdropImage(
    backdropUrl: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // The main image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .crossfade(true)
                .placeholder(R.drawable.globo)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.7f), // Start with semi-transparent black
                            Background // Transition to transparent
                        )
                    )
                )
        )
    }
}

@Preview
@Composable
fun BackdropImagePreview() {
    BackdropImage(
        backdropUrl = "",
        modifier = Modifier.fillMaxWidth()
    )

}