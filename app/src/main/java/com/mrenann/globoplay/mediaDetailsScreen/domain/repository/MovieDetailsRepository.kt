package com.mrenann.globoplay.mediaDetailsScreen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetails(id: Int): MediaDetails
    suspend fun getMoviesSimilar(id: Int, pagingConfig: PagingConfig): Flow<PagingData<Media>>

}