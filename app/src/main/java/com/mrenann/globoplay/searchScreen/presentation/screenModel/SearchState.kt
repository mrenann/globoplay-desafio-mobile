package com.mrenann.globoplay.searchScreen.presentation.screenModel

import androidx.paging.PagingData
import com.mrenann.globoplay.core.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val query: String = "",
    val medias: Flow<PagingData<Media>> = emptyFlow()
)