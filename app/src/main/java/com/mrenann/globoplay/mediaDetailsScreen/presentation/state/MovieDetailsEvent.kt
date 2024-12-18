package com.mrenann.globoplay.mediaDetailsScreen.presentation.state

sealed class MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()
    data class GetTvDetails(val tvId: Int) : MovieDetailsEvent()
}
