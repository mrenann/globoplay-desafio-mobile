package com.mrenann.globoplay.myListScreen.data.mapper

import com.mrenann.globoplay.core.data.local.entity.MediaEntity
import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.core.domain.model.Media

fun List<MediaEntity>.toMediasFromMovie() = map { entity ->
    Media(
        id = entity.id,
        name = entity.title,
        overview = "",
        posterPath = entity.imageUrl,
        voteAverage = 0.0,
        type = if (entity.type == MediaType.MOVIE) "movie" else "tv",
    )

}

fun List<MediaEntity>.toMediasFromTv() = map { entity ->
    Media(
        id = entity.id,
        name = entity.title,
        overview = "",
        posterPath = entity.imageUrl,
        voteAverage = 0.0,
        type = "tv",
    )
}

fun Media.toMediaEntity(type: MediaType): MediaEntity {
    return MediaEntity(
        id = id,
        title = name,
        imageUrl = posterPath,
        type = type,
    )
}