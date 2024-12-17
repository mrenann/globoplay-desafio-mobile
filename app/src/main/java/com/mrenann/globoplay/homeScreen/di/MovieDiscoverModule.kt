package com.mrenann.globoplay.homeScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.homeScreen.data.repository.MovieDiscoverRepositoryImpl
import com.mrenann.globoplay.homeScreen.data.source.MovieDiscoverRemoteDataSourceImpl
import com.mrenann.globoplay.homeScreen.domain.repository.MovieDiscoverRepository
import com.mrenann.globoplay.homeScreen.domain.source.MovieDiscoverRemoteDataSource
import com.mrenann.globoplay.homeScreen.domain.usecase.GetMovieDiscoverUseCase
import com.mrenann.globoplay.homeScreen.domain.usecase.GetMovieDiscoverUseCaseImpl
import org.koin.dsl.module

val MovieDiscoverModule =
    module {

        single<MovieDiscoverRemoteDataSource> {
            MovieDiscoverRemoteDataSourceImpl(
                service = get<MediaService>(),
            )
        }

        single<MovieDiscoverRepository> {
            MovieDiscoverRepositoryImpl(
                remoteDataSource = get<MovieDiscoverRemoteDataSource>(),
            )
        }

        single<GetMovieDiscoverUseCase> {
            GetMovieDiscoverUseCaseImpl(
                repository = get<MovieDiscoverRepository>(),
            )
        }
    }
