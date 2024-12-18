package com.mrenann.globoplay.core.data.remote

import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.data.remote.response.MovieDetailsResponse
import com.mrenann.globoplay.core.data.remote.response.SearchResponse
import com.mrenann.globoplay.core.data.remote.response.TvDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MediaService {
    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @QueryMap queryParams: Map<String, String>,
    ): DiscoverMediaResponse<TVResult>

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @QueryMap queryParams: Map<String, String>,
    ): DiscoverMediaResponse<MovieResult>

    @GET("search/{movie_id}")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String
    ): SearchResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getMoviesSimilar(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): DiscoverMediaResponse<MovieResult>

    @GET("tv/{series_id}")
    suspend fun getTvSerie(
        @Path("series_id") movieId: Int
    ): TvDetailsResponse

    @GET("tv/{series_id}/similar")
    suspend fun getSeriesSimilar(
        @Path("series_id") movieId: Int,
        @Query("page") page: Int
    ): DiscoverMediaResponse<TVResult>
}
