package com.mrenann.globoplay.myListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mrenann.globoplay.myListScreen.presentation.components.MoviesContent
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel.State
import com.mrenann.globoplay.ui.theme.Background

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieFavoriteScreenModel>()

        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(Color.Black),
                    title = {
                        Text("Minha lista", color = Color.White)
                    },
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = Color.White,
                        ),
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Background)
                    .padding(paddingValues)
            ) {
                when (state) {
                    is State.Init -> {
                        Text("INIT")
                    }

                    is State.Loading -> {
                        Text("LOADING")
                    }

                    is State.Result -> {
                        MoviesContent(
                            pagingMovies = (state as State.Result).state.movies,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }


    }
}