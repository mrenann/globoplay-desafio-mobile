package com.mrenann.globoplay.searchScreen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.searchScreen.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCaseImpl(
    private val repository: SearchRepository,
) : SearchUseCase {
    override fun invoke(params: SearchUseCase.Params): Flow<PagingData<Media>> {
        return repository.getSearch(
            query = params.query,
            pagingConfig =
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    initialLoadSize = 20,
                ),
        )
    }
}
