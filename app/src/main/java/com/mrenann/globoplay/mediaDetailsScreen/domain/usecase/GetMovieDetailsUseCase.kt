package com.mrenann.globoplay.mediaDetailsScreen.domain.usecase

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MovieDetails
import com.mrenann.globoplay.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Media>>, MovieDetails>>>
    data class Params(val movieId: Int)

}