package com.mrenann.globoplay.mediaDetailsScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.paging.TvSimilarPagingSource
import com.mrenann.globoplay.core.util.toBackdropUrl
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.TvDetailsRemoteDataSource

class TvDetailsRemoteDataSourceImpl(
    private val service: MediaService
) : TvDetailsRemoteDataSource {
    override suspend fun getTvDetails(id: Int): MediaDetails {
        val response = service.getTvSerie(id)
        val genres = response.genres.map { genre -> genre.name }
        return MediaDetails(
            id = response.id,
            title = response.name,
            genres = genres,
            overview = response.overview,
            backdropPath = response.backdropPath?.toBackdropUrl(),
            releaseDate = response.firstAirDate,
            originalTitle = response.originalName,
            countries = response.originCountry,
            duration = 0,
        )
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