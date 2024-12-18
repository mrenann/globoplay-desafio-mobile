package com.mrenann.globoplay.homeScreen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface TvDiscoverRepository {
    fun getTvDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>>

    fun getTvBrazilianDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>>
}
