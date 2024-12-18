package com.mrenann.globoplay.homeScreen.domain.usecase

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface GetMovieDiscoverUseCase {
    operator fun invoke(): Flow<PagingData<Media>>

    fun invokeBrazilian(): Flow<PagingData<Media>>
}
