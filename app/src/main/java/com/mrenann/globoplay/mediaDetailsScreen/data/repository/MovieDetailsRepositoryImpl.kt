package com.mrenann.globoplay.mediaDetailsScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.MovieDetailsRepository
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepositoryImpl(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(id: Int): MediaDetails {
        return remoteDataSource.getMovieDetails(id)
    }

    override suspend fun getMoviesSimilar(
        id: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Media>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarMoviesPagingSource(id = id)
            }
        ).flow
    }
}