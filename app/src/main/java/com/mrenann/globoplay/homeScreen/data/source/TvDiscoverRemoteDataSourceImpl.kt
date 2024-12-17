package com.mrenann.globoplay.homeScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.paging.TvBrazilianPagingSource
import com.mrenann.globoplay.core.paging.TvPagingSource
import com.mrenann.globoplay.core.util.Constants.ORIGINAL_LANGUAGE_PARAM
import com.mrenann.globoplay.core.util.Constants.ORIGINAL_LANGUAGE_VALUE
import com.mrenann.globoplay.core.util.Constants.ORIGINCOUNTRY_PARAM
import com.mrenann.globoplay.core.util.Constants.ORIGINCOUNTRY_VALUE
import com.mrenann.globoplay.core.util.Constants.WITHNETWORKS_PARAM
import com.mrenann.globoplay.core.util.Constants.WITHNETWORKS_VALUE
import com.mrenann.globoplay.homeScreen.domain.source.TvDiscoverRemoteDataSource

class TvDiscoverRemoteDataSourceImpl(
    private val service: MediaService
) : TvDiscoverRemoteDataSource {

    override fun getPagingSource(): TvPagingSource {
        return TvPagingSource(this)
    }

    override fun getBrazilianPagingSource(): TvBrazilianPagingSource {
        return TvBrazilianPagingSource(this)
    }

    override suspend fun getDiscover(
        page: Int,
        isFromBrazil: Boolean
    ): DiscoverMediaResponse<TVResult> {
        val queryParams = mutableMapOf("page" to page.toString())

        if (isFromBrazil) {
            queryParams[WITHNETWORKS_PARAM] = WITHNETWORKS_VALUE
            queryParams[ORIGINCOUNTRY_PARAM] = ORIGINCOUNTRY_VALUE
            queryParams[ORIGINAL_LANGUAGE_PARAM] = ORIGINAL_LANGUAGE_VALUE
        }

        return service.getDiscoverTv(
            queryParams = queryParams
        )
    }

}