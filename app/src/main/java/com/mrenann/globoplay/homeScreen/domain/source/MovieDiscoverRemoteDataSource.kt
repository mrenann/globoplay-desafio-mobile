package com.mrenann.globoplay.homeScreen.domain.source

import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.paging.MovieBrazilianPagingSource
import com.mrenann.globoplay.core.paging.MoviePagingSource

interface MovieDiscoverRemoteDataSource : DiscoverRemoteDataSource<MovieResult> {

    override fun getPagingSource(): MoviePagingSource
    override fun getBrazilianPagingSource(): MovieBrazilianPagingSource

    override suspend fun getDiscover(
        page: Int,
        isFromBrazil: Boolean
    ): DiscoverMediaResponse<MovieResult>

}