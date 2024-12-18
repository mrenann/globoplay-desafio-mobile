package com.mrenann.globoplay.mediaDetailsScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.mediaDetailsScreen.data.repository.MovieDetailsRepositoryImpl
import com.mrenann.globoplay.mediaDetailsScreen.data.source.MovieDetailsRemoteDataSourceImpl
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.MovieDetailsRepository
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.MovieDetailsRemoteDataSource
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCaseImpl
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetTvDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.presentation.screenModels.DetailsScreenModel
import com.mrenann.globoplay.myListScreen.domain.usecase.AddMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.DeleteMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.InListMovieFavoriteUseCase
import org.koin.dsl.module

val MovieDetailsModule =
    module {

        single<MovieDetailsRemoteDataSource> {
            MovieDetailsRemoteDataSourceImpl(
                service = get<MediaService>(),
            )
        }

        single<MovieDetailsRepository> {
            MovieDetailsRepositoryImpl(
                remoteDataSource = get<MovieDetailsRemoteDataSource>(),
            )
        }

        single<GetMovieDetailsUseCase> {
            GetMovieDetailsUseCaseImpl(
                repository = get<MovieDetailsRepository>(),
            )
        }

        factory {
            DetailsScreenModel(
                getMovieDetailsUseCase = get<GetMovieDetailsUseCase>(),
                getTvDetailsUseCase = get<GetTvDetailsUseCase>(),
                addMovieFavoriteUseCase = get<AddMovieFavoriteUseCase>(),
                removeMovieFavoriteUseCase = get<DeleteMovieFavoriteUseCase>(),
                inListMovieUseCase = get<InListMovieFavoriteUseCase>(),
            )
        }

    }
