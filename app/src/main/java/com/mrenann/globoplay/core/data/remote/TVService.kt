package com.mrenann.globoplay.core.data.remote

import com.mrenann.globoplay.core.data.remote.response.DiscoverTVResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TVService {

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @Query("page") page: Int
    ): DiscoverTVResponse

}