package com.mrenann.globoplay.homeScreen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MovieDiscoverRepository {
    fun getMovieDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>>
    fun getMovieBrazilianDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>>

}