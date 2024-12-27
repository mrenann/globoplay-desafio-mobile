@file:kotlin.OptIn(ExperimentalMaterial3Api::class)

package com.mrenann.globoplay.videoScreen.presentation

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.globoplay.core.domain.model.Videos


data class VideoScreen(
    val video: Videos?,
) : Screen {
    @kotlin.OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var areControlsVisible by remember { mutableStateOf(true) }

        Scaffold(
            topBar = {
                if (areControlsVisible) {
                    TopAppBar(
                        title = { Text(video?.name ?: "Video Title") },
                        colors = TopAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.Transparent,
                            navigationIconContentColor = Color.White,
                            titleContentColor = Color.White,
                            actionIconContentColor = Color.White
                        ),
                        navigationIcon = {
                            IconButton(
                                modifier = Modifier.padding(vertical = 20.dp),
                                onClick = { navigator.pop() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
            },
        ) {
            // Pass areControlsVisible and a function to update it to PlayerWithControls
            PlayerWithControls(video) { newVisibility ->
                areControlsVisible = newVisibility
            }
        }
    }
}

@OptIn(UnstableApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlayerWithControls(
    video: Videos?,
    onControlsVisibilityChange: (Boolean) -> Unit,
) {
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    var isFullscreen by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    )

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            playWhenReady = true
            prepare()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Video Player
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .clickable { isFullscreen = !isFullscreen }, // Toggle fullscreen on click
            factory = {
                PlayerView(context).also { playerView ->
                    playerView.player = exoPlayer
                    playerView.useController = true

                    playerView.setControllerVisibilityListener(
                        PlayerView.ControllerVisibilityListener { visibility ->
                            val visibilityState = visibility == View.VISIBLE
                            onControlsVisibilityChange(visibilityState)
                        }
                    )
                }
            },
            update = { playerView ->
                when (lifecycle) {
                    Lifecycle.Event.ON_RESUME -> {
                        playerView.onResume()
                        playerView.player?.play()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        playerView.onPause()
                        playerView.player?.pause()
                    }

                    else -> Unit
                }

                if (isFullscreen) {
                    playerView.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                } else {
                    playerView.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (context.resources.displayMetrics.widthPixels * 9 / 16)
                    )
                }
            }
        )
    }
}
