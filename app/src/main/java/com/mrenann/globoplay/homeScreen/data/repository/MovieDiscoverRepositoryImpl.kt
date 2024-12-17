package com.mrenann.globoplay.homeScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.domain.repository.MovieDiscoverRepository
import com.mrenann.globoplay.homeScreen.domain.source.MovieDiscoverRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieDiscoverRepositoryImpl(
    private val remoteDataSource: MovieDiscoverRemoteDataSource
) : MovieDiscoverRepository {

    override fun getMovieDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPagingSource()
            }
        ).flow

    }

    override fun getMovieBrazilianDiscover(pagingConfig: PagingConfig): Flow<PagingData<Media>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getBrazilianPagingSource()
            }
        ).flow

    }

}