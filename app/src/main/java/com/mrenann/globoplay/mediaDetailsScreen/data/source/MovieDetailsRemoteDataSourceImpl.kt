package com.mrenann.globoplay.mediaDetailsScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.domain.model.Videos
import com.mrenann.globoplay.core.paging.MovieSimilarPagingSource
import com.mrenann.globoplay.core.util.toBackdropUrl
import com.mrenann.globoplay.core.util.toPosterUrl
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.MovieDetailsRemoteDataSource

class MovieDetailsRemoteDataSourceImpl(
    private val service: MediaService,
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(id: Int): MediaDetails {
        val response = service.getMovie(id)
        val videos = getMovieVideos(id = id)
        val genres = response.genres.map { genre -> genre.name }

        return MediaDetails(
            id = response.id,
            title = response.title,
            genres = genres,
            overview = response.overview,
            backdropPath = response.backdropPath.toBackdropUrl(),
            posterPath = response.posterPath.toPosterUrl(),
            releaseDate = response.releaseDate,
            originalTitle = response.originalTitle,
            countries = response.originCountry,
            duration = response.runtime,
            type = "movie",
            videos = videos
        )
    }

    override suspend fun getMovieVideos(id: Int): List<Videos> {
        val response = service.getMovieVideos(movieId = id)
        val videos = response.results.map { video ->
            Videos(
                id = video.id,
                key = video.key,
                name = video.name,
                site = video.site,
                type = video.type
            )
        }
        return videos
    }

    override suspend fun getMoviesSimilar(page: Int, id: Int): DiscoverMediaResponse<MovieResult> {
        return service.getMoviesSimilar(page = page, movieId = id)
    }

    override fun getSimilarMoviesPagingSource(id: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(
            remoteDataSource = this, id = id
        )
    }
}