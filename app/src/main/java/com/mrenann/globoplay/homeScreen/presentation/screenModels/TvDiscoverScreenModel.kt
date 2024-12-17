package com.mrenann.globoplay.homeScreen.presentation.screenModels

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.homeScreen.domain.usecase.GetTvDiscoverUseCase
import com.mrenann.globoplay.homeScreen.presentation.state.TvDiscoverState

class TvDiscoverScreenModel(
    getTvDiscoverUseCase: GetTvDiscoverUseCase
) : StateScreenModel<TvDiscoverScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val state: TvDiscoverState) : State()
    }

    init {
        val series = getTvDiscoverUseCase.invoke().cachedIn(screenModelScope)
        val state = TvDiscoverState(series)
        mutableState.value = State.Result(state)

    }
}