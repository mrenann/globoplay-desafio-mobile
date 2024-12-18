package com.mrenann.globoplay.myListScreen.data.source

import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.data.local.dao.TvDao
import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.myListScreen.data.mapper.toMediaEntity
import com.mrenann.globoplay.myListScreen.data.mapper.toMediasFromMovie
import com.mrenann.globoplay.myListScreen.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieFavoriteLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val tvDao: TvDao
) : MovieFavoriteLocalDataSource {
    override fun getMovies(): Flow<List<Media>> {
        return movieDao.getMovies().map {
            it.toMediasFromMovie()
        }
    }

    override suspend fun insertMovie(movie: Media, type: MediaType) {
        movieDao.insertMovie(movie.toMediaEntity(type))
    }

    override suspend fun inList(movieId: Int, type: MediaType): Boolean {
        return movieDao.inList(
            movieId, type
        ) != null
    }

    override suspend fun delete(movie: Media, type: MediaType) {
        movieDao.deleteMovie(
            movie.toMediaEntity(type)
        )
    }
}