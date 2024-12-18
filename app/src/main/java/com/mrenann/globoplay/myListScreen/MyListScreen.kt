package com.mrenann.globoplay.myListScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mrenann.globoplay.initialScreen.presentation.LocalNavigatorParent
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel

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
        val navigator = LocalNavigatorParent.currentOrThrow
        val screenModel = koinScreenModel<MovieFavoriteScreenModel>()

        val state by screenModel.state.collectAsState()

        Text(
            "Lista"
        )

    }
}