package com.mrenann.globoplay.searchScreen.di

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.searchScreen.data.repository.SearchRepositoryImpl
import com.mrenann.globoplay.searchScreen.data.source.SearchRemoteDataSourceImpl
import com.mrenann.globoplay.searchScreen.domain.repository.SearchRepository
import com.mrenann.globoplay.searchScreen.domain.source.SearchRemoteDataSource
import com.mrenann.globoplay.searchScreen.domain.usecase.SearchUseCase
import com.mrenann.globoplay.searchScreen.domain.usecase.SearchUseCaseImpl
import com.mrenann.globoplay.searchScreen.presentation.screenModel.SearchScreenModel
import org.koin.dsl.module

val searchModule =
    module {

        single<SearchRemoteDataSource> {
            SearchRemoteDataSourceImpl(
                service = get<MediaService>(),
            )
        }

        single<SearchRepository> {
            SearchRepositoryImpl(
                remoteDataSource = get<SearchRemoteDataSource>(),
            )
        }

        single<SearchUseCase> {
            SearchUseCaseImpl(
                repository = get<SearchRepository>(),
            )
        }

        factory {
            SearchScreenModel(
                getSearchUseCase = get<SearchUseCase>()
            )
        }
    }
