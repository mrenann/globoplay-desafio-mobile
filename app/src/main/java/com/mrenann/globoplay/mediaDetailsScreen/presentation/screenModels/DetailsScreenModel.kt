package com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetTvDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.presentation.state.MovieDetailsEvent
import com.mrenann.globoplay.mediaDetailsScreen.presentation.state.MovieDetailsState
import kotlinx.coroutines.launch

class DetailsScreenModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTvDetailsUseCase: GetTvDetailsUseCase
) : StateScreenModel<DetailsScreenModel.State>(State.Init) {
    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val state: MovieDetailsState) : State()
    }

    fun getMovieDetails(getMovieDetails: MovieDetailsEvent.GetMovieDetails) {
        event(getMovieDetails)
    }

    fun getTvDetails(getMovieDetails: MovieDetailsEvent.GetTvDetails) {
        event(getMovieDetails)
    }


    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetails -> {
                screenModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {

                            is ResultData.Success -> {
                                mutableState.value = State.Result(
                                    MovieDetailsState(
                                        isLoading = false,
                                        movie = resultData.data.second,
                                        results = resultData.data.first
                                    )
                                )
                            }

                            is ResultData.Failure -> {
                                mutableState.value = State.Result(
                                    MovieDetailsState(
                                        isLoading = false,
                                        error = resultData.exception?.message.toString()
                                    )
                                )
                            }

                            is ResultData.Loading -> {
                                mutableState.value = State.Loading
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.GetTvDetails -> {
                screenModelScope.launch {
                    getTvDetailsUseCase.invoke(
                        params = GetTvDetailsUseCase.Params(
                            tvId = event.tvId
                        )
                    ).collect { resultData ->
                        when (resultData) {

                            is ResultData.Success -> {
                                mutableState.value = State.Result(
                                    MovieDetailsState(
                                        isLoading = false,
                                        movie = resultData.data.second,
                                        results = resultData.data.first
                                    )
                                )
                            }

                            is ResultData.Failure -> {
                                mutableState.value = State.Result(
                                    MovieDetailsState(
                                        isLoading = false,
                                        error = resultData.exception?.message.toString()
                                    )
                                )
                            }

                            is ResultData.Loading -> {
                                mutableState.value = State.Loading
                            }
                        }
                    }
                }
            }
        }
    }

}
