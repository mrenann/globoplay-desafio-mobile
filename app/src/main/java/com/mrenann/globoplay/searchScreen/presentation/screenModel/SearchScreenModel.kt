package com.mrenann.globoplay.searchScreen.presentation.screenModel

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels.DetailsScreenModel.State
import com.mrenann.globoplay.searchScreen.domain.usecase.SearchUseCase
import com.mrenann.globoplay.searchScreen.presentation.SearchEvent
import kotlinx.coroutines.flow.emptyFlow

class SearchScreenModel(
    private val getSearchUseCase: SearchUseCase
) : StateScreenModel<SearchScreenModel.State>(State.Init) {
    sealed class State {
        object Init : State()

        object Loading : State()

        data class Result(val state: SearchState) : State()
    }


    fun fetch(query: String = "") {
        val medias = getSearchUseCase.invoke(
            params = SearchUseCase.Params(
                query = query
            )
        ).cachedIn(screenModelScope)
        val currentState = mutableState.value
        if (currentState is State.Result) {
            mutableState.value = State.Result(
                currentState.state.copy(
                    medias = medias
                )
            )
        } else {
            mutableState.value = State.Result(
                SearchState(
                    medias = medias,
                    query = query
                )
            )
        }

    }

    fun event(event: SearchEvent) {
        when (event) {
            is SearchEvent.EnteredQuery -> {
                val currentState = mutableState.value
                mutableState.value = if (currentState is State.Result) {
                    State.Result(
                        currentState.state.copy(
                            query = event.query
                        )
                    )
                } else {
                    State.Result(
                        SearchState(
                            query = event.query,
                            medias = emptyFlow()
                        )
                    )
                }
            }
        }
    }

}

