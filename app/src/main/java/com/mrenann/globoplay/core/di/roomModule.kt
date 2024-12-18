package com.mrenann.globoplay.core.di

import androidx.room.Room
import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.data.local.dao.TvDao
import com.mrenann.globoplay.core.data.local.databases.ListDatabase
import com.mrenann.globoplay.core.util.Constants.LIST_DATABASE_NAME
import org.koin.dsl.module


val roomModule = module {
    single {
        Room.databaseBuilder(get(), ListDatabase::class.java, LIST_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single<MovieDao> {
        val database = get<ListDatabase>()
        database.movieDao()
    }
    single<TvDao> {
        val database = get<ListDatabase>()
        database.tvDao()
    }
}