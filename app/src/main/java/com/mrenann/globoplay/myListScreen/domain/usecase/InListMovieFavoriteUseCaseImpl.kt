package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InListMovieFavoriteUseCaseImpl(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : InListMovieFavoriteUseCase {
    override suspend fun invoke(params: InListMovieFavoriteUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            val inList = movieFavoriteRepository.inList(params.id, params.type)
            emit(ResultData.Success(inList))
        }.flowOn(Dispatchers.IO)
    }
}