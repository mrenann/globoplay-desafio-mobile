package com.mrenann.globoplay.homeScreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.mrenann.globoplay.R
import com.mrenann.globoplay.core.util.BASE_AVATAR_URL
import com.mrenann.globoplay.homeScreen.presentation.components.ContentGrid
import com.mrenann.globoplay.homeScreen.presentation.screenModels.DiscoverScreenModel
import com.mrenann.globoplay.homeScreen.presentation.screenModels.DiscoverScreenModel.State
import com.mrenann.globoplay.ui.theme.Background


object HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val listState = rememberLazyListState()

        // Detect whether the user has scrolled down
        val isScrolled by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 1 || listState.firstVisibleItemScrollOffset > 1 }
        }

        // Background color for the top bar
        val appBarBackgroundColor = if (isScrolled) Color.Black else Color.Transparent
        val textAppBarColor = if (isScrolled) Color.White else Color.Black

        val screenModel = koinScreenModel<DiscoverScreenModel>()
        val state by screenModel.state.collectAsState()

        Box(
            Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                topBar = {
                    TopAppBar(
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.globoplay_logo),
                                contentDescription = "Globoplay Logo",
                                contentScale = ContentScale.Crop, // Adjust scaling
                                modifier = Modifier.fillMaxWidth(0.5F)
                            )
                        },
                        modifier = Modifier
                            .background(appBarBackgroundColor) // Dynamic background color
                            .zIndex(1f), // Ensure the app bar stays on top
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent, // Keep transparent to avoid overriding
                            titleContentColor = textAppBarColor
                        ),
                        actions = {
                            Row {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        tint = Color.White,
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "Localized description"
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        tint = Color.White,
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Localized description"
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(BASE_AVATAR_URL)
                                            .crossfade(true)
                                            .placeholder(R.drawable.avatar)
                                            .error(R.drawable.avatar)
                                            .build(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(24.dp),
                                        contentScale = ContentScale.FillHeight,
                                    )
                                }
                            }
                        },
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                        .padding(innerPadding) // Adjust for the top bar
                ) {
                    items(1) { index ->
                        when (state) {
                            is State.Init -> {}
                            is State.Loading -> {
                                Column(
                                    Modifier.fillMaxSize()
                                ) {
                                    Text("CARREGANDO...")
                                }
                            }

                            is State.Result -> {
                                val series =
                                    (state as State.Result).state.tvSeries.collectAsLazyPagingItems()
                                val seriesBr =
                                    (state as State.Result).state.tvSeriesFromBrazil.collectAsLazyPagingItems()

                                val movies =
                                    (state as State.Result).state.movies.collectAsLazyPagingItems()
                                val moviesBr =
                                    (state as State.Result).state.moviesFromBrazil.collectAsLazyPagingItems()

                                ContentGrid(
                                    title = "Séries",
                                    pagingItems = series,
                                    paddingValues = PaddingValues(0.dp),
                                    onClick = { id -> println(id) }
                                )
                                ContentGrid(
                                    title = "Séries do Brasil",
                                    pagingItems = seriesBr,
                                    paddingValues = PaddingValues(0.dp),
                                    onClick = { id -> println(id) }
                                )
                                ContentGrid(
                                    title = "Filmes",
                                    pagingItems = movies,
                                    paddingValues = PaddingValues(0.dp),
                                    onClick = { id -> println(id) }
                                )
                                ContentGrid(
                                    title = "Filmes do Brasil",
                                    pagingItems = moviesBr,
                                    paddingValues = PaddingValues(0.dp),
                                    onClick = { id -> println(id) }
                                )
                            }
                        }
                    }
                }
            }

        }


    }

}