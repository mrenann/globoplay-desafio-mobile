package com.mrenann.globoplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.globoplay.core.di.networkModule
import com.mrenann.globoplay.core.di.roomModule
import com.mrenann.globoplay.homeScreen.di.MovieDiscoverModule
import com.mrenann.globoplay.homeScreen.di.TvDiscoverModule
import com.mrenann.globoplay.initialScreen.presentation.InitialScreen
import com.mrenann.globoplay.mediaDetailsScreen.di.MovieDetailsModule
import com.mrenann.globoplay.mediaDetailsScreen.di.TvDetailsModule
import com.mrenann.globoplay.myListScreen.di.favoriteModule
import com.mrenann.globoplay.ui.theme.GloboplayTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

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
                    favoriteModule
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        setContent {
            GloboplayTheme {
                Navigator(InitialScreen)
            }
        }
    }
}
