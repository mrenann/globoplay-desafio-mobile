package com.mrenann.globoplay.di

import androidx.room.Room
import androidx.test.espresso.core.internal.deps.dagger.Provides
import com.mrenann.globoplay.core.data.local.databases.ListDatabase
import org.koin.dsl.module
import javax.inject.Named

@get:Provides
@Named("test_db")
val TestAppModule = module {

    single {
        Room.inMemoryDatabaseBuilder(get(), ListDatabase::class.java).allowMainThreadQueries()
            .build()

    }
}