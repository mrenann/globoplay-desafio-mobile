package com.mrenann.globoplay.mediaDetailsScreen.presentation.state

import com.mrenann.globoplay.core.domain.model.Media

sealed class MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()
    data class GetTvDetails(val tvId: Int) : MovieDetailsEvent()
    data class AddFavorite(val media: Media) : MovieDetailsEvent()
    data class CheckedFavorite(val id: Int) : MovieDetailsEvent()
    data class RemoveFavorite(val media: Media) : MovieDetailsEvent()
}
