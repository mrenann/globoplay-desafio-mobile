package com.mrenann.globoplay.myListScreen.di

import com.mrenann.globoplay.myListScreen.data.repository.MovieFavoriteRepositoryImpl
import com.mrenann.globoplay.myListScreen.data.source.MovieFavoriteLocalDataSourceImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.mrenann.globoplay.myListScreen.domain.usecase.InListMovieFavoriteUseCaseImpl
import org.koin.dsl.module

val favoriteModule = module {

    single { MovieFavoriteLocalDataSourceImpl(movieDao = get()) }

    single { MovieFavoriteRepositoryImpl(localDataSource = get()) }

    single { GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository = get()) }
    single { AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = get()) }
    single { InListMovieFavoriteUseCaseImpl(movieFavoriteRepository = get()) }
    single { DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository = get()) }

}