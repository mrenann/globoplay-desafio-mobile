package com.mrenann.globoplay.mediaDetailsScreen.domain.source

import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.paging.TvSimilarPagingSource

interface TvDetailsRemoteDataSource {

    suspend fun getTvDetails(id: Int): MediaDetails
    suspend fun getTvSeriesSimilar(page: Int, id: Int): DiscoverMediaResponse<TVResult>
    fun getSimilarTvPagingSource(id: Int): TvSimilarPagingSource

}