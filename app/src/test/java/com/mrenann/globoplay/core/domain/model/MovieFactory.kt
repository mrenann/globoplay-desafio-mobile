package com.mrenann.globoplay.core.domain.model

class MovieFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Grinch -> {
            Media(
                id = 1,
                name = "Grinch",
                overview = "",
                posterPath = "",
                voteAverage = 2.2,
                type = "movie"
            )
        }

        Poster.TropaDeElite -> {
            Media(
                id = 1,
                name = "TropaDeElite",
                overview = "",
                posterPath = "",
                voteAverage = 2.2,
                type = "movie"
            )
        }
    }

    sealed class Poster {
        object TropaDeElite : Poster()
        object Grinch : Poster()
    }
}