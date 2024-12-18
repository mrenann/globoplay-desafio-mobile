package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface GetMoviesFavoriteUseCase {
    operator fun invoke(): Flow<List<Media>>
}