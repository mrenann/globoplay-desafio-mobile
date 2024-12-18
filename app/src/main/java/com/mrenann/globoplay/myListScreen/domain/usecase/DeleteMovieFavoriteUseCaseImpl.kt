package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeleteMovieFavoriteUseCaseImpl(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val insert = movieFavoriteRepository.delete(params.media, params.type)
            emit(ResultData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}