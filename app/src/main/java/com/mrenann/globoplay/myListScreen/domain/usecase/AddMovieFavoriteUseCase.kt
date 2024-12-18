package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface AddMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val media: Media)
}