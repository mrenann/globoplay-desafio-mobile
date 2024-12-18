package com.mrenann.globoplay.myListScreen.data.repository

import com.mrenann.globoplay.core.data.local.entity.MediaType
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

    override suspend fun insertMovie(movie: Media, type: MediaType) {
        return localDataSource.insertMovie(movie, type)
    }

    override suspend fun inList(movieId: Int, type: MediaType): Boolean {
        return localDataSource.inList(movieId, type)
    }

    override suspend fun delete(movie: Media, type: MediaType) {
        return localDataSource.delete(movie, type)
    }

}