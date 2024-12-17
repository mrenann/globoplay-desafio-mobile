package com.mrenann.globoplay.mediaDetailsScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.mediaDetailsScreen.data.repository.MovieDetailsRepositoryImpl
import com.mrenann.globoplay.mediaDetailsScreen.data.source.MovieDetailsRemoteDataSourceImpl
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.MovieDetailsRepository
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.MovieDetailsRemoteDataSource
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetMovieDetailsUseCaseImpl
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

    }