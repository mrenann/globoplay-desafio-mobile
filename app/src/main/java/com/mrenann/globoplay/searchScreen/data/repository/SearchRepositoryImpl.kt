package com.mrenann.globoplay.searchScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.searchScreen.domain.repository.SearchRepository
import com.mrenann.globoplay.searchScreen.domain.source.SearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource,
) : SearchRepository {
    override fun getSearch(query: String, pagingConfig: PagingConfig): Flow<PagingData<Media>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPagingSource(query = query)
            },
        ).flow
    }

}
