package com.mrenann.globoplay.myListScreen.presentation.state

import com.mrenann.globoplay.core.domain.model.Media

data class FavoriteState(
    val movies: List<Media> = emptyList(),
)