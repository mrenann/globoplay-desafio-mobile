package com.mrenann.globoplay.myListScreen.data.repository

import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import com.mrenann.globoplay.myListScreen.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow

class MovieFavoriteRepositoryImpl(
    private val localDataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {
    override fun getMovies(): Flow<List<Media>> {
        return localDataSource.getMovies()
    }

    override suspend fun insertMovie(movie: Media) {
        return localDataSource.insertMovie(movie)
    }

    override suspend fun inList(movieId: Int): Boolean {
        return localDataSource.inList(movieId)
    }

    override suspend fun delete(movie: Media) {
        return localDataSource.delete(movie)
    }

}