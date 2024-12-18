package com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.util.ResultData
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetTvDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.presentation.state.MovieDetailsEvent
import com.mrenann.globoplay.mediaDetailsScreen.presentation.state.MovieDetailsState
import com.mrenann.globoplay.myListScreen.domain.usecase.AddMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.DeleteMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.InListMovieFavoriteUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsScreenModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTvDetailsUseCase: GetTvDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val removeMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val inListMovieUseCase: InListMovieFavoriteUseCase
) : StateScreenModel<DetailsScreenModel.State>(State.Init) {
    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val state: MovieDetailsState) : State()
    }

    fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    fun getMovieDetails(getMovieDetails: MovieDetailsEvent.GetMovieDetails) {
        event(getMovieDetails)
    }

    fun getTvDetails(getTvDetails: MovieDetailsEvent.GetTvDetails) {
        event(getTvDetails)
    }

    fun favorite(media: Media) {
        if (state.value is State.Result) {
            if ((state.value as State.Result).state.checked) {
                event(MovieDetailsEvent.RemoveFavorite(media))
            } else {
                event(MovieDetailsEvent.AddFavorite(media))
            }
        }

    }


    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.AddFavorite -> {
                screenModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            media = event.media,
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Failure -> {}
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            checked = true
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.RemoveFavorite -> {
                screenModelScope.launch {
                    removeMovieFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            media = event.media,
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Failure -> {}
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            checked = false
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.CheckedFavorite -> {
                screenModelScope.launch {
                    inListMovieUseCase.invoke(
                        params = InListMovieFavoriteUseCase.Params(
                            id = event.id
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Failure -> {}
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            checked = result.data
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.GetMovieDetails -> {
                screenModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {

                            is ResultData.Success -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            isLoading = false, // Set loading to false after the request completes
                                            movie = resultData.data.second,
                                            results = resultData.data.first
                                        )
                                    )
                                } else {
                                    // If the state was not in Result before, set it directly
                                    mutableState.value = State.Result(
                                        MovieDetailsState(
                                            isLoading = false,
                                            movie = resultData.data.second,
                                            results = resultData.data.first
                                        )
                                    )
                                }
                            }

                            is ResultData.Failure -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            isLoading = false, // Set loading to false in case of failure
                                            error = resultData.exception?.message.toString()
                                        )
                                    )
                                } else {
                                    // If the state was not in Result before, set it directly
                                    mutableState.value = State.Result(
                                        MovieDetailsState(
                                            isLoading = false,
                                            error = resultData.exception?.message.toString()
                                        )
                                    )
                                }
                            }

                            is ResultData.Loading -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(isLoading = true)
                                    )
                                } else {
                                    // If the state was not in Result before, set it directly
                                    mutableState.value = State.Result(
                                        MovieDetailsState(isLoading = true)
                                    )
                                }
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
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            isLoading = false, // Set loading to false after the request completes
                                            movie = resultData.data.second, // Update the movie data
                                            results = resultData.data.first // Update the results data
                                        )
                                    )
                                } else {
                                    // If the state was not in Result before, set it directly
                                    mutableState.value = State.Result(
                                        MovieDetailsState(
                                            isLoading = false,
                                            movie = resultData.data.second,
                                            results = resultData.data.first
                                        )
                                    )
                                }
                            }

                            is ResultData.Failure -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(
                                            isLoading = false,
                                            error = resultData.exception?.message.toString()
                                        )
                                    )
                                } else {
                                    mutableState.value = State.Result(
                                        MovieDetailsState(
                                            isLoading = false,
                                            error = resultData.exception?.message.toString()
                                        )
                                    )
                                }
                            }

                            is ResultData.Loading -> {
                                val currentState = mutableState.value
                                if (currentState is State.Result) {
                                    mutableState.value = State.Result(
                                        currentState.state.copy(isLoading = true)
                                    )
                                } else {
                                    mutableState.value = State.Result(
                                        MovieDetailsState(isLoading = true)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
