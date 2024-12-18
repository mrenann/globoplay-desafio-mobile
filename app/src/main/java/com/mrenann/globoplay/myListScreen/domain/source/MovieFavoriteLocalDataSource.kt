package com.mrenann.globoplay.myListScreen.domain.source

import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteLocalDataSource {

    fun getMovies(): Flow<List<Media>>
    suspend fun insertMovie(movie: Media, type: MediaType)
    suspend fun inList(movieId: Int, type: MediaType): Boolean
    suspend fun delete(movie: Media, type: MediaType)

}