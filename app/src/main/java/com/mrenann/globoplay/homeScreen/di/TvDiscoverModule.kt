package com.mrenann.globoplay.homeScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.homeScreen.data.repository.TvDiscoverRepositoryImpl
import com.mrenann.globoplay.homeScreen.data.source.TvDiscoverRemoteDataSourceImpl
import com.mrenann.globoplay.homeScreen.domain.repository.TvDiscoverRepository
import com.mrenann.globoplay.homeScreen.domain.source.TvDiscoverRemoteDataSource
import com.mrenann.globoplay.homeScreen.domain.usecase.GetMovieDiscoverUseCase
import com.mrenann.globoplay.homeScreen.domain.usecase.GetTvDiscoverUseCase
import com.mrenann.globoplay.homeScreen.domain.usecase.GetTvDiscoverUseCaseImpl
import com.mrenann.globoplay.homeScreen.presentation.screenModels.DiscoverScreenModel
import org.koin.dsl.module

val TvDiscoverModule = module {

    single<TvDiscoverRemoteDataSource> {
        TvDiscoverRemoteDataSourceImpl(
            service = get<MediaService>()
        )
    }


    single<TvDiscoverRepository> {
        TvDiscoverRepositoryImpl(
            remoteDataSource = get<TvDiscoverRemoteDataSource>()
        )
    }

    single<GetTvDiscoverUseCase> {
        GetTvDiscoverUseCaseImpl(
            repository = get<TvDiscoverRepository>()
        )

    }

    factory {
        DiscoverScreenModel(
            getTvDiscoverUseCase = get<GetTvDiscoverUseCase>(),
            getMovieDiscoverUseCase = get<GetMovieDiscoverUseCase>()
        )
    }

}