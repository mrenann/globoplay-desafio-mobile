package com.mrenann.globoplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.globoplay.initialScreen.presentation.InitialScreen
import com.mrenann.globoplay.ui.theme.GloboplayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            GloboplayTheme {
                Navigator(InitialScreen)
            }
        }
    }
}
