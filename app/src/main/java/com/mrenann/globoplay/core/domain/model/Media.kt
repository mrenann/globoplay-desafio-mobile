package com.mrenann.globoplay.core.domain.model

data class Media(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double,
    val type: String,
)
