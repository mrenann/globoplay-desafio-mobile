package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface InListMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val id: Int, val type: MediaType)
}