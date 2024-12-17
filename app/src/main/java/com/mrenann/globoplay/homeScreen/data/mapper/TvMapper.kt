package com.mrenann.globoplay.homeScreen.data.mapper

import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.domain.model.TVSerie
import com.mrenann.globoplay.core.util.toPosterUrl

fun List<TVResult>.toTVSerie() = map { tv ->
    TVSerie(
        id = tv.id,
        name = tv.name,
        overview = tv.overview,
        posterPath = tv.posterPath.toPosterUrl(),
        voteAverage = tv.voteAverage
    )
}