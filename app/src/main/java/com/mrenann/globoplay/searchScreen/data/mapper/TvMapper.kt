package com.mrenann.globoplay.searchScreen.data.mapper

import com.mrenann.globoplay.core.data.remote.model.SearchResult
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.util.toPosterUrl

fun List<SearchResult>.toSearchFromMovie() =
    map { tv ->
        Media(
            id = tv.id,
            name = tv.title ?: tv.name ?: "",
            overview = tv.overview,
            posterPath = tv.posterPath.toPosterUrl(),
            voteAverage = tv.voteAverage,
            type = if (tv.mediaType == "tv") "tv" else "movie",
        )
    }
