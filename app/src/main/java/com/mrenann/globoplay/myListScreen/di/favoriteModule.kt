package com.mrenann.globoplay.myListScreen.di

import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.myListScreen.data.repository.MovieFavoriteRepositoryImpl
import com.mrenann.globoplay.myListScreen.data.source.MovieFavoriteLocalDataSourceImpl
import com.mrenann.globoplay.myListScreen.domain.repository.MovieFavoriteRepository
import com.mrenann.globoplay.myListScreen.domain.source.MovieFavoriteLocalDataSource
import com.mrenann.globoplay.myListScreen.domain.usecase.AddMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.DeleteMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.GetMoviesFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.InListMovieFavoriteUseCase
import com.mrenann.globoplay.myListScreen.domain.usecase.InListMovieFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.presentation.screenModel.MovieFavoriteScreenModel
import org.koin.dsl.module

val favoriteModule = module {

    single<MovieFavoriteLocalDataSource> {
        MovieFavoriteLocalDataSourceImpl(movieDao = get<MovieDao>())
    }

    single<MovieFavoriteRepository> {
        MovieFavoriteRepositoryImpl(localDataSource = get<MovieFavoriteLocalDataSource>())
    }

    single<GetMoviesFavoriteUseCase> { GetMoviesFavoriteUseCaseImpl(repository = get<MovieFavoriteRepository>()) }
    single<AddMovieFavoriteUseCase> { AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = get<MovieFavoriteRepository>()) }
    single<InListMovieFavoriteUseCase> { InListMovieFavoriteUseCaseImpl(movieFavoriteRepository = get<MovieFavoriteRepository>()) }
    single<DeleteMovieFavoriteUseCase> { DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository = get<MovieFavoriteRepository>()) }

    factory {
        MovieFavoriteScreenModel(
            getMoviesFavoriteUseCase = get<GetMoviesFavoriteUseCase>()
        )
    }
}