package com.mrenann.globoplay.mediaDetailsScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.TvDetailsRepository
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.TvDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class TvDetailsRepositoryImpl(
    private val remoteDataSource: TvDetailsRemoteDataSource
) : TvDetailsRepository {
    override suspend fun getTvDetails(id: Int): MediaDetails {
        return remoteDataSource.getTvDetails(id)
    }

    override suspend fun getTvSeriesSimilar(
        id: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Media>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarTvPagingSource(id = id)
            }
        ).flow
    }
}