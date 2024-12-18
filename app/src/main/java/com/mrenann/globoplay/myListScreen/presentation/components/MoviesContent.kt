package com.mrenann.globoplay.myListScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.presentation.components.ContentItem

@Composable
fun MoviesContent(
    pagingMovies: List<Media>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp) // Constrain height to prevent infinite height
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.size) { index ->
                val movie = pagingMovies[index]
                movie.let {
                    ContentItem(
                        id = it.id,
                        posterUrl = it.posterPath,
                        onClick = {}
                    )
                }
            }
        }
    }
}