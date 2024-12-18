package com.mrenann.globoplay

import android.app.Application
import com.mrenann.globoplay.core.di.networkModule
import com.mrenann.globoplay.core.di.roomModule
import com.mrenann.globoplay.homeScreen.di.MovieDiscoverModule
import com.mrenann.globoplay.homeScreen.di.TvDiscoverModule
import com.mrenann.globoplay.mediaDetailsScreen.di.MovieDetailsModule
import com.mrenann.globoplay.mediaDetailsScreen.di.TvDetailsModule
import com.mrenann.globoplay.myListScreen.di.favoriteModule
import com.mrenann.globoplay.searchScreen.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GloboplayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            try {
                androidContext(applicationContext)
                modules(
                    networkModule,
                    MovieDiscoverModule,
                    TvDiscoverModule,
                    MovieDetailsModule,
                    TvDetailsModule,
                    roomModule,
                    favoriteModule,
                    searchModule
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
