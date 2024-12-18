package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesFavoriteUseCaseImpl(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Media>> {
        return movieFavoriteRepository.getMovies()
    }

}