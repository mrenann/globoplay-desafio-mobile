package com.mrenann.globoplay.homeScreen.data.mapper

import com.mrenann.globoplay.core.data.remote.model.MovieResult
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.util.toPosterUrl

fun List<TVResult>.toSerieFromTv() =
    map { tv ->
        Media(
            id = tv.id,
            name = tv.name,
            overview = tv.overview,
            posterPath = tv.posterPath.toPosterUrl(),
            voteAverage = tv.voteAverage,
        )
    }

fun List<MovieResult>.toSerieFromMovie() =
    map { movie ->
        Media(
            id = movie.id,
            name = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath.toPosterUrl(),
            voteAverage = movie.voteAverage,
        )
    }
