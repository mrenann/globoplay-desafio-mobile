package com.mrenann.globoplay.mediaDetailsScreen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import kotlinx.coroutines.flow.Flow

interface TvDetailsRepository {

    suspend fun getTvDetails(id: Int): MediaDetails
    suspend fun getTvSeriesSimilar(id: Int, pagingConfig: PagingConfig): Flow<PagingData<Media>>

}