package com.mrenann.globoplay.homeScreen.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.mrenann.globoplay.homeScreen.presentation.components.ContentGrid
import com.mrenann.globoplay.homeScreen.presentation.screenModels.TvDiscoverScreenModel
import com.mrenann.globoplay.homeScreen.presentation.screenModels.TvDiscoverScreenModel.State


object HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<TvDiscoverScreenModel>()
        val state by screenModel.state.collectAsState()



        when (state) {
            is State.Init -> {

            }

            is State.Loading -> {

            }

            is State.Result -> {
                val series = (state as State.Result).state.tvSeries.collectAsLazyPagingItems()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Filme")
                            },

                            )
                    },
                    content = { paddingValues ->
                        ContentGrid(
                            pagingItems = series,
                            paddingValues = paddingValues,
                            onClick = { id -> println(id) }
                        )
                    }
                )
            }
        }


    }

}