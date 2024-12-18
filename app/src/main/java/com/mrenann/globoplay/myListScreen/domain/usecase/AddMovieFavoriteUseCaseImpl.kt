package com.mrenann.globoplay.myListScreen.domain.usecase

import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AddMovieFavoriteUseCaseImpl(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val insert = movieFavoriteRepository.insertMovie(params.media)
            emit(ResultData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}