package com.mrenann.globoplay.homeScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.paging.MovieBrazilianPagingSource
import com.mrenann.globoplay.core.paging.MoviePagingSource
import com.mrenann.globoplay.core.util.Constants.ORIGINAL_LANGUAGE_PARAM
import com.mrenann.globoplay.core.util.Constants.ORIGINAL_LANGUAGE_VALUE
import com.mrenann.globoplay.core.util.Constants.ORIGINCOUNTRY_PARAM
import com.mrenann.globoplay.core.util.Constants.ORIGINCOUNTRY_VALUE
import com.mrenann.globoplay.homeScreen.domain.source.MovieDiscoverRemoteDataSource

class MovieDiscoverRemoteDataSourceImpl(
    private val service: MediaService,
) : MovieDiscoverRemoteDataSource {
    override fun getPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override fun getBrazilianPagingSource(): MovieBrazilianPagingSource {
        return MovieBrazilianPagingSource(this)
    }

    override suspend fun getDiscover(
        page: Int,
        isFromBrazil: Boolean,
    ): DiscoverMediaResponse<MovieResult> {
        val queryParams = mutableMapOf("page" to page.toString())

        if (isFromBrazil) {
            queryParams[ORIGINCOUNTRY_PARAM] = ORIGINCOUNTRY_VALUE
            queryParams[ORIGINAL_LANGUAGE_PARAM] = ORIGINAL_LANGUAGE_VALUE
        }

        return service.getDiscoverMovie(
            queryParams = queryParams,
        )
    }
}
