package com.mrenann.globoplay.homeScreen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.domain.repository.MovieDiscoverRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDiscoverUseCaseImpl(
    private val repository: MovieDiscoverRepository,
) : GetMovieDiscoverUseCase {
    override fun invoke(): Flow<PagingData<Media>> {
        return repository.getMovieDiscover(
            pagingConfig =
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    initialLoadSize = 20,
                ),
        )
    }

    override fun invokeBrazilian(): Flow<PagingData<Media>> {
        return repository.getMovieBrazilianDiscover(
            pagingConfig =
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    initialLoadSize = 20,
                ),
        )
    }
}
