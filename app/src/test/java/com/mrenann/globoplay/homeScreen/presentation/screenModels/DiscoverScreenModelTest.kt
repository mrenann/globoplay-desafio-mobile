package com.mrenann.globoplay.homeScreen.presentation.screenModels

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.mrenann.globoplay.TestDispatcherRule
import com.mrenann.globoplay.core.domain.model.MovieFactory
import com.mrenann.globoplay.core.domain.model.TvFactory
import com.mrenann.globoplay.homeScreen.domain.usecase.GetMovieDiscoverUseCase
import com.mrenann.globoplay.homeScreen.domain.usecase.GetTvDiscoverUseCase
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DiscoverScreenModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getTvDiscoverUseCase: GetTvDiscoverUseCase

    @Mock
    lateinit var getMovieDiscoverUseCase: GetMovieDiscoverUseCase

    private val screenModel by lazy {
        DiscoverScreenModel(getTvDiscoverUseCase, getMovieDiscoverUseCase)

    }

    private val fakePagingDataMovie = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.TropaDeElite),
            MovieFactory().create(poster = MovieFactory.Poster.Grinch)
        )
    )

    private val fakePagingDataTv = PagingData.from(
        listOf(
            TvFactory().create(poster = TvFactory.Poster.Novela),
            TvFactory().create(poster = TvFactory.Poster.Serie)
        )
    )

    @Test
    fun should_return_valid_DiscoverScreen_state_with_TV_and_Movie_data_from_use_cases() =
        runTest {
            whenever(getTvDiscoverUseCase.invoke()).thenReturn(
                flowOf(fakePagingDataTv)
            )

            whenever(getMovieDiscoverUseCase.invoke()).thenReturn(
                flowOf(fakePagingDataMovie)
            )

            whenever(getTvDiscoverUseCase.invokeBrazilian()).thenReturn(
                flowOf(fakePagingDataTv)
            )

            whenever(getMovieDiscoverUseCase.invokeBrazilian()).thenReturn(
                flowOf(fakePagingDataMovie)
            )

            val result = screenModel.state.value

            if (result is DiscoverScreenModel.State.Result) {
                assertThat(result.state.moviesFromBrazil.first()).isNotNull()
                assertThat(result.state.movies.first()).isNotNull()
                assertThat(result.state.tvSeriesFromBrazil.first()).isNotNull()
                assertThat(result.state.tvSeries.first()).isNotNull()
            }
        }

}