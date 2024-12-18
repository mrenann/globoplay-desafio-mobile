package com.mrenann.globoplay.mediaDetailsScreen.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.mrenann.globoplay.mediaDetailsScreen.presentation.components.MovieContent
import com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels.DetailsScreenModel
import com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels.DetailsScreenModel.State
import com.mrenann.globoplay.mediaDetailsScreen.presentation.state.MovieDetailsEvent

data class DetailsScreen(
    val movieId: Int?
) : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<DetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(
            key1 = movieId
        ) {
            if (movieId != null) {
                screenModel.getMovieDetails(MovieDetailsEvent.GetMovieDetails(movieId))
            }
        }

        Scaffold(
            content = {
                when (state) {
                    is State.Init -> {
                        Text("INICIO...")
                    }

                    is State.Loading -> {
                        Text("LOADING...")
                    }

                    is State.Result -> {
                        val movie = (state as State.Result).state.movie
                        val results =
                            (state as State.Result).state.results.collectAsLazyPagingItems()

                        MovieContent(
                            movie = movie,
                            pagingMoviesSimilar = results,
                            isLoading = false,
                            isError = "",
                            iconColor = Color.Black,
                            modifier = Modifier,

                            ) { }
                    }
                }
            }
        )

    }
}