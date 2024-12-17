package com.mrenann.globoplay.homeScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.TVSerie
import com.mrenann.globoplay.homeScreen.domain.repository.TvDiscoverRepository
import com.mrenann.globoplay.homeScreen.domain.source.TvDiscoverRemoteDataSource
import kotlinx.coroutines.flow.Flow

class TvDiscoverRepositoryImpl(
    private val remoteDataSource: TvDiscoverRemoteDataSource
) : TvDiscoverRepository {

    override fun getTvDiscover(pagingConfig: PagingConfig): Flow<PagingData<TVSerie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getDiscoverTvPagingSource()
            }
        ).flow

    }

}