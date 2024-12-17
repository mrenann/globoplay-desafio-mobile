package com.mrenann.globoplay.core.data.remote

import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MediaService {

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @QueryMap queryParams: Map<String, String>
    ): DiscoverMediaResponse<TVResult>

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @QueryMap queryParams: Map<String, String>
    ): DiscoverMediaResponse<MovieResult>


}