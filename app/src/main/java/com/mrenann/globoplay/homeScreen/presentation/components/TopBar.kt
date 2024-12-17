package com.mrenann.globoplay.homeScreen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.mrenann.globoplay.R
import com.mrenann.globoplay.core.util.BASE_AVATAR_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isScrolled: Boolean) {
    val appBarBackgroundColor = if (isScrolled) Color.Black else Color.Transparent
    val textAppBarColor = if (isScrolled) Color.White else Color.Black

    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.globoplay_logo),
                contentDescription = "Globoplay Logo",
                contentScale = ContentScale.Crop, // Adjust scaling
                modifier = Modifier.fillMaxWidth(0.5F),
            )
        },
        modifier =
            Modifier
                .background(appBarBackgroundColor) // Dynamic background color
                .zIndex(1f),
        // Ensure the app bar stays on top
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent, // Keep transparent to avoid overriding
                titleContentColor = textAppBarColor,
            ),
        actions = {
            Row {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    AsyncImage(
                        model =
                            ImageRequest.Builder(LocalContext.current)
                                .data(BASE_AVATAR_URL)
                                .crossfade(true)
                                .placeholder(R.drawable.avatar)
                                .error(R.drawable.avatar)
                                .build(),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .clip(CircleShape)
                                .size(24.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                }
            }
        },
    )
}