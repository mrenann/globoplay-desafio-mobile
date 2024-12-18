package com.mrenann.globoplay.core.di

import android.content.Context
import androidx.room.Room
import com.mrenann.globoplay.core.data.local.databases.ListDatabase
import com.mrenann.globoplay.core.util.Constants.LIST_DATABASE_NAME
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get<Context>(), ListDatabase::class.java, LIST_DATABASE_NAME).build()
    }

    single { get<ListDatabase>().movieDao() }
    single { get<ListDatabase>().tvDao() }
}