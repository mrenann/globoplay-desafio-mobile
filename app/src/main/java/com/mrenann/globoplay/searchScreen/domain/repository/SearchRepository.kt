package com.mrenann.globoplay.searchScreen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearch(query: String, pagingConfig: PagingConfig): Flow<PagingData<Media>>
}
