package com.mrenann.globoplay.mediaDetailsScreen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.TvDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetTvDetailsUseCaseImpl(
    private val repository: TvDetailsRepository
) : GetTvDetailsUseCase {
    override fun invoke(params: GetTvDetailsUseCase.Params): Flow<ResultData<Pair<Flow<PagingData<Media>>, MediaDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val details = repository.getTvDetails(params.tvId)
                val similar = repository.getTvSeriesSimilar(
                    id = params.tvId,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
                emit(
                    ResultData.Success(
                        similar to details
                    )
                )
            } catch (e: HttpException) {
                emit(ResultData.Failure(e))
            } catch (e: IOException) {
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO)

    }

}