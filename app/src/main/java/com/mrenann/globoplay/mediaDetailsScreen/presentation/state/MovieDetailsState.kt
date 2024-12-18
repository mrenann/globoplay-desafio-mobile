package com.mrenann.globoplay.mediaDetailsScreen.presentation.state

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailsState(
    val movie: MediaDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val checked: Boolean = false,
    val results: Flow<PagingData<Media>> = emptyFlow()
)
