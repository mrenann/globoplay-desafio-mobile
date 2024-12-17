package com.mrenann.globoplay.homeScreen.domain.usecase

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.TVSerie
import kotlinx.coroutines.flow.Flow

interface GetTvDiscoverUseCase {
    operator fun invoke(): Flow<PagingData<TVSerie>>
}