package com.mrenann.globoplay.searchScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.presentation.components.ErrorView
import com.mrenann.globoplay.core.presentation.components.LoadingView
import com.mrenann.globoplay.homeScreen.presentation.components.ContentItem
import com.mrenann.globoplay.mediaDetailsScreen.presentation.DetailsScreen

@Composable
fun SearchContent(
    pagingMovies: LazyPagingItems<Media>,
    modifier: Modifier = Modifier,
    navigator: Navigator
) {

    Column(
        modifier = modifier

    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie.let { movie ->
                    ContentItem(
                        id = movie?.id ?: 1,
                        posterUrl = movie?.posterPath ?: "",
                        onClick = {
                            navigator.push(
                                DetailsScreen(
                                    movieId = if (movie?.type == "movie") movie.id else null,
                                    tvId = if (movie?.type == "tv") movie.id else null,
                                )
                            )
                        }
                    )
                }
            }

            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.prepend is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorView(
                                modifier = Modifier.padding(10.dp),
                                message = "Tente Novamente"
                            ) {
                                retry()
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorView(
                                modifier = Modifier.padding(10.dp),
                                message = "Tente Novamente"
                            ) {
                                retry()
                            }
                        }
                    }
                }
            }
        }
    }
}