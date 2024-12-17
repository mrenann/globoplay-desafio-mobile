package com.mrenann.globoplay.homeScreen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.domain.repository.TvDiscoverRepository
import kotlinx.coroutines.flow.Flow

class GetTvDiscoverUseCaseImpl(
    private val repository: TvDiscoverRepository
) : GetTvDiscoverUseCase {
    override fun invoke(): Flow<PagingData<Media>> {
        return repository.getTvDiscover(
            pagingConfig = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20,
            )
        )
    }

    override fun invokeBrazilian(): Flow<PagingData<Media>> {
        return repository.getTvBrazilianDiscover(
            pagingConfig = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20,
            )
        )
    }
}