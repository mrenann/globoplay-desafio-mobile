package com.mrenann.globoplay.homeScreen.domain.source

import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.paging.TvBrazilianPagingSource
import com.mrenann.globoplay.core.paging.TvPagingSource

interface TvDiscoverRemoteDataSource : DiscoverRemoteDataSource<TVResult> {

    override fun getPagingSource(): TvPagingSource
    override fun getBrazilianPagingSource(): TvBrazilianPagingSource

    override suspend fun getDiscover(
        page: Int,
        isFromBrazil: Boolean
    ): DiscoverMediaResponse<TVResult>

}