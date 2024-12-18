package com.mrenann.globoplay.mediaDetailsScreen.data.mapper

import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MovieDetails

fun MovieDetails.toMedia(): Media =
    Media(
        id = id,
        name = title,
        overview = overview ?: "",
        posterPath = backdropPath.toString(),
        voteAverage = 0.0
    )