package com.mrenann.globoplay.homeScreen.data.source

import com.mrenann.globoplay.core.data.remote.TVService
import com.mrenann.globoplay.core.data.remote.response.DiscoverTVResponse
import com.mrenann.globoplay.core.paging.TvPagingSource
import com.mrenann.globoplay.homeScreen.domain.source.TvDiscoverRemoteDataSource

class TvDiscoverRemoteDataSourceImpl(
    private val service: TVService
) : TvDiscoverRemoteDataSource {

    override fun getDiscoverTvPagingSource(): TvPagingSource {
        return TvPagingSource(this)
    }

    override suspend fun getDiscoverTv(page: Int): DiscoverTVResponse {
        return service.getDiscoverTv(page = page)
    }

}