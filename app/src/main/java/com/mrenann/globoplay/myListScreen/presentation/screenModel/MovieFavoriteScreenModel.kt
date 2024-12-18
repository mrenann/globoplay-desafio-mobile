package com.mrenann.globoplay.myListScreen.presentation.screenModel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.myListScreen.domain.usecase.GetMoviesFavoriteUseCase
import com.mrenann.globoplay.myListScreen.presentation.state.FavoriteState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieFavoriteScreenModel(
    private val getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase
) : StateScreenModel<MovieFavoriteScreenModel.State>(State.Init) {
    sealed class State {
        object Init : State()

        object Loading : State()

        data class Result(val state: FavoriteState) : State()
    }

    init {
        fetch()

    }

    private fun fetch() {
        screenModelScope.launch {
            getMoviesFavoriteUseCase.invoke().collectLatest { movies ->
                mutableState.value = State.Result(
                    state = FavoriteState(
                        movies = movies
                    )
                )
            }
        }
    }

}

