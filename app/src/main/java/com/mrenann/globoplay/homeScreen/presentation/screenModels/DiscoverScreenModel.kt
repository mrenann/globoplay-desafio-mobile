package com.mrenann.globoplay.homeScreen.presentation.screenModels

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.homeScreen.domain.usecase.GetMovieDiscoverUseCase
import com.mrenann.globoplay.homeScreen.domain.usecase.GetTvDiscoverUseCase
import com.mrenann.globoplay.homeScreen.presentation.state.DiscoverState

class DiscoverScreenModel(
    getTvDiscoverUseCase: GetTvDiscoverUseCase,
    getMovieDiscoverUseCase: GetMovieDiscoverUseCase
) : StateScreenModel<DiscoverScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val state: DiscoverState) : State()
    }

    init {
        mutableState.value = State.Loading

        val series = getTvDiscoverUseCase.invoke().cachedIn(screenModelScope)
        val brazilianSeries = getTvDiscoverUseCase.invokeBrazilian().cachedIn(screenModelScope)

        val movies = getMovieDiscoverUseCase.invoke().cachedIn(screenModelScope)
        val brazilianMovies = getMovieDiscoverUseCase.invokeBrazilian().cachedIn(screenModelScope)

        val state = DiscoverState(
            tvSeries = series,
            tvSeriesFromBrazil = brazilianSeries,
            movies = movies,
            moviesFromBrazil = brazilianMovies
        )
        mutableState.value = State.Result(state)

    }
}