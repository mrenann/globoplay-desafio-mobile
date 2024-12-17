package com.mrenann.globoplay.homeScreen.presentation.state

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DiscoverState(
    val tvSeries: Flow<PagingData<Media>> = emptyFlow(),
    val tvSeriesFromBrazil: Flow<PagingData<Media>> = emptyFlow(),
    val movies: Flow<PagingData<Media>> = emptyFlow(),
    val moviesFromBrazil: Flow<PagingData<Media>> = emptyFlow(),
)
