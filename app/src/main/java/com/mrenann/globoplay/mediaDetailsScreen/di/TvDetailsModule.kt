package com.mrenann.globoplay.mediaDetailsScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.mediaDetailsScreen.data.repository.TvDetailsRepositoryImpl
import com.mrenann.globoplay.mediaDetailsScreen.data.source.TvDetailsRemoteDataSourceImpl
import com.mrenann.globoplay.mediaDetailsScreen.domain.repository.TvDetailsRepository
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.TvDetailsRemoteDataSource
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetTvDetailsUseCase
import com.mrenann.globoplay.mediaDetailsScreen.domain.usecase.GetTvDetailsUseCaseImpl
import org.koin.dsl.module

val TvDetailsModule =
    module {

        single<TvDetailsRemoteDataSource> {
            TvDetailsRemoteDataSourceImpl(
                service = get<MediaService>(),
            )
        }

        single<TvDetailsRepository> {
            TvDetailsRepositoryImpl(
                remoteDataSource = get<TvDetailsRemoteDataSource>(),
            )
        }

        single<GetTvDetailsUseCase> {
            GetTvDetailsUseCaseImpl(
                repository = get<TvDetailsRepository>(),
            )
        }


    }
