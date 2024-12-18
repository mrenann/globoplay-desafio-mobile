package com.mrenann.globoplay.myListScreen.data.source

import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.myListScreen.data.mapper.toMediasFromMovie
import com.mrenann.globoplay.myListScreen.data.mapper.toMovieEntity
import com.mrenann.globoplay.myListScreen.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieFavoriteLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieFavoriteLocalDataSource {
    override fun getMovies(): Flow<List<Media>> {
        return movieDao.getMovies().map {
            it.toMediasFromMovie()
        }
    }

    override suspend fun insertMovie(movie: Media) {
        movieDao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun inList(movieId: Int): Boolean {
        return movieDao.inList(
            movieId
        ) != null
    }

    override suspend fun delete(movie: Media) {
        movieDao.deleteMovie(
            movie.toMovieEntity()
        )
    }
}