package com.mrenann.globoplay.searchScreen.domain.usecase

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<Media>>
    data class Params(val query: String)
}
