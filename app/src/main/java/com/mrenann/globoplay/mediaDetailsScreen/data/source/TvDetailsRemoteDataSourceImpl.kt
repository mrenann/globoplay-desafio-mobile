package com.mrenann.globoplay.mediaDetailsScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.domain.model.Videos
import com.mrenann.globoplay.core.paging.TvSimilarPagingSource
import com.mrenann.globoplay.core.util.toBackdropUrl
import com.mrenann.globoplay.core.util.toPosterUrl
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.TvDetailsRemoteDataSource

class TvDetailsRemoteDataSourceImpl(
    private val service: MediaService,
) : TvDetailsRemoteDataSource {
    override suspend fun getTvDetails(id: Int): MediaDetails {
        val response = service.getTvSerie(id)
        val videos = getTvSeriesVideos(id = id)
        val genres = response.genres.map { genre -> genre.name }

        return MediaDetails(
            id = response.id,
            title = response.name,
            genres = genres,
            overview = response.overview,
            backdropPath = response.backdropPath?.toBackdropUrl(),
            posterPath = response.posterPath?.toPosterUrl(),
            releaseDate = response.firstAirDate,
            originalTitle = response.originalName,
            countries = response.originCountry,
            duration = 0,
            type = "tv",
            videos = videos
        )
    }

    override suspend fun getTvSeriesVideos(id: Int): List<Videos> {
        val response = service.getTvShowVideos(movieId = id)
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

    override suspend fun getTvSeriesSimilar(page: Int, id: Int): DiscoverMediaResponse<TVResult> {
        return service.getSeriesSimilar(page = page, movieId = id)
    }

    override fun getSimilarTvPagingSource(id: Int): TvSimilarPagingSource {
        return TvSimilarPagingSource(
            remoteDataSource = this, id = id
        )
    }
}