package com.mrenann.globoplay.core.domain.model

class TvFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Serie -> {
            Media(
                id = 1,
                name = "Serie",
                overview = "",
                posterPath = "",
                voteAverage = 2.2,
                type = "tv"
            )
        }

        Poster.Novela -> {
            Media(
                id = 1,
                name = "Novela",
                overview = "",
                posterPath = "",
                voteAverage = 2.2,
                type = "tv"
            )
        }
    }

    sealed class Poster {
        object Novela : Poster()
        object Serie : Poster()
    }
}