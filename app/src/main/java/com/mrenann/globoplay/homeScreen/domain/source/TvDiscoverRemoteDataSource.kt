package com.mrenann.globoplay.homeScreen.domain.source

import com.mrenann.globoplay.core.data.remote.response.DiscoverTVResponse
import com.mrenann.globoplay.core.paging.TvPagingSource

interface TvDiscoverRemoteDataSource {

    fun getDiscoverTvPagingSource(): TvPagingSource

    suspend fun getDiscoverTv(page: Int): DiscoverTVResponse

}