package com.mrenann.globoplay.myListScreen.data.mapper

import com.mrenann.globoplay.core.data.local.entity.MovieEntity
import com.mrenann.globoplay.core.data.local.entity.TvEntity
import com.mrenann.globoplay.core.domain.model.Media

fun List<MovieEntity>.toMediasFromMovie() = map { entity ->
    Media(
        id = entity.movieId,
        name = entity.title,
        overview = "",
        posterPath = entity.imageUrl,
        voteAverage = 0.0,
        type = "movie",
    )

}

fun List<TvEntity>.toMediasFromTv() = map { entity ->
    Media(
        id = entity.tvId,
        name = entity.title,
        overview = "",
        posterPath = entity.imageUrl,
        voteAverage = 0.0,
        type = "movie",
    )
}

fun Media.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = id,
        title = name,
        imageUrl = posterPath,
    )
}