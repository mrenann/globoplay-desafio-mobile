package com.mrenann.globoplay.mediaDetailsScreen.presentation.state

import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.core.domain.model.Media

sealed class MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()
    data class GetTvDetails(val tvId: Int) : MovieDetailsEvent()
    data class AddFavorite(val media: Media, val type: MediaType) : MovieDetailsEvent()
    data class CheckedFavorite(val id: Int, val type: MediaType) : MovieDetailsEvent()
    data class RemoveFavorite(val media: Media, val type: MediaType) : MovieDetailsEvent()
}
