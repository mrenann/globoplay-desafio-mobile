package com.mrenann.globoplay.core.domain.model

data class MediaDetails(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val originalTitle: String,
    val duration: Int = 0,
    val countries: List<String> = emptyList()

)