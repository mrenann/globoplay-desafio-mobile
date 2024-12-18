package com.mrenann.globoplay.homeScreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.globoplay.homeScreen.presentation.components.ContentGrid
import com.mrenann.globoplay.homeScreen.presentation.components.TopBar
import com.mrenann.globoplay.homeScreen.presentation.screenModels.DiscoverScreenModel
import com.mrenann.globoplay.homeScreen.presentation.screenModels.DiscoverScreenModel.State
import com.mrenann.globoplay.mediaDetailsScreen.presentation.DetailsScreen
import com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels.DetailsScreenModel
import com.mrenann.globoplay.ui.theme.Background

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val listState = rememberLazyListState()
        val isScrolled by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 1 || listState.firstVisibleItemScrollOffset > 1 }
        }
        val screenModel = koinScreenModel<DiscoverScreenModel>()
        val screenModelDetails = koinScreenModel<DetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        Box(
            Modifier
                .fillMaxSize()
                .background(Background),
        ) {
            Scaffold(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                topBar = {
                    TopBar(isScrolled)
                },
            ) { innerPadding ->
                LazyColumn(
                    state = listState,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Background)
                            .padding(innerPadding), // Adjust for the top bar
                ) {
                    items(1) { index ->
                        when (state) {
                            is State.Init -> {}
                            is State.Loading -> {
                                Column(
                                    Modifier.fillMaxSize(),
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
                                val getMovieDetails = screenModelDetails::getMovieDetails
                                ContentGrid(
                                    title = "Séries",
                                    pagingItems = series,
                                    onClick = { id ->
                                        navigator.push(
                                            DetailsScreen(
                                                tvId = id,
                                            )
                                        )
                                    },
                                )
                                ContentGrid(
                                    title = "Séries do Brasil",
                                    pagingItems = seriesBr,
                                    onClick = { id ->
                                        navigator.push(
                                            DetailsScreen(
                                                tvId = id,
                                            )
                                        )
                                    },
                                )
                                ContentGrid(
                                    title = "Filmes",
                                    pagingItems = movies,
                                    onClick = { id ->
                                        navigator.push(
                                            DetailsScreen(
                                                movieId = id,
                                            )
                                        )
                                    },
                                )
                                ContentGrid(
                                    title = "Filmes do Brasil",
                                    pagingItems = moviesBr,
                                    onClick = { id ->
                                        navigator.push(
                                            DetailsScreen(
                                                movieId = id,
                                            )
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
