package com.mrenann.globoplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.globoplay.core.di.networkModule
import com.mrenann.globoplay.homeScreen.di.MovieDiscoverModule
import com.mrenann.globoplay.homeScreen.di.TvDiscoverModule
import com.mrenann.globoplay.homeScreen.presentation.HomeScreen
import com.mrenann.globoplay.ui.theme.GloboplayTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            try {
                modules(
                    networkModule,
                    MovieDiscoverModule,
                    TvDiscoverModule,
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        setContent {
            Navigator(HomeScreen)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GloboplayTheme { // asasdasdasd
    }
}
