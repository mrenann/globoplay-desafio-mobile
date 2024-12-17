package com.mrenann.globoplay.homeScreen.presentation.state

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.TVSerie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TvDiscoverState(
    val tvSeries: Flow<PagingData<TVSerie>> = emptyFlow()
)

