package com.mrenann.globoplay.mediaDetailsScreen.domain.source

import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    suspend fun getMovieDetails(id: Int): MediaDetails
    suspend fun getMoviesSimilar(page: Int, id: Int): DiscoverMediaResponse<MovieResult>
    fun getSimilarMoviesPagingSource(id: Int): MovieSimilarPagingSource

}