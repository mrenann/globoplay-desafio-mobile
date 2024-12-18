package com.mrenann.globoplay.searchScreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.globoplay.searchScreen.presentation.components.SearchComponent
import com.mrenann.globoplay.searchScreen.presentation.components.SearchContent
import com.mrenann.globoplay.searchScreen.presentation.screenModel.SearchScreenModel
import com.mrenann.globoplay.ui.theme.Background

object SearchScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White,
                    ),
                    modifier = Modifier.heightIn(90.dp),
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
                    },
                    title = {
                        SearchComponent(
                            modifier = Modifier,
                            query = if (state is SearchScreenModel.State.Result) (state as SearchScreenModel.State.Result).state.query else "",
                            onQueryChange = { query ->
                                screenModel.event(query)
                            },
                            onSearch = {
                                screenModel.fetch(it)
                            }
                        )
                    }
                )
            },
            modifier = Modifier
                .background(Background)
        ) { paddingValues ->

            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                when (state) {
                    is SearchScreenModel.State.Init -> {
                    }

                    is SearchScreenModel.State.Loading -> {
                        Text("LOADING")
                    }

                    is SearchScreenModel.State.Result -> {
                        SearchContent(
                            pagingMovies = (state as SearchScreenModel.State.Result).state.medias.collectAsLazyPagingItems(),
                            modifier = Modifier,
                            navigator = navigator
                        )
                    }
                }
            }


        }

    }

}