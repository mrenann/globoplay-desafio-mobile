package com.mrenann.globoplay.myListScreen.domain.repository

import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository {
    fun getMovies(): Flow<List<Media>>
    suspend fun insertMovie(movie: Media)
    suspend fun inList(movieId: Int): Boolean
    suspend fun delete(movie: Media)

}