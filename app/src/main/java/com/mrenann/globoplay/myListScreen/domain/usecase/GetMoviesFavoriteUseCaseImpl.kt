package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesFavoriteUseCaseImpl(
    private val repository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override fun invoke(): Flow<List<Media>> {
        return repository.getMovies()
    }

}