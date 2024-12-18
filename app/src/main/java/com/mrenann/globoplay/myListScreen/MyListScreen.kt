package com.mrenann.globoplay.myListScreen

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mrenann.globoplay.myListScreen.presentation.components.MoviesContent
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel.State

object MyListScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Minha Lista"
            val icon = rememberVectorPainter(Icons.Default.Star)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieFavoriteScreenModel>()

        val state by screenModel.state.collectAsState()

        when (state) {
            is State.Init -> {
                Text("INIT")
            }

            is State.Loading -> {
                Text("LOADING")
            }

            is State.Result -> {
                Text("RESULT")
                Log.i("asdasdsad", "${(state as State.Result).state.movies}")
                MoviesContent(
                    pagingMovies = (state as State.Result).state.movies
                )
            }
        }

    }
}