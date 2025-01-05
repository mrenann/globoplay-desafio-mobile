package com.mrenann.globoplay.homeScreen.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Home

interface ResettableTab {
    val resetToRoot: () -> Unit
}

object HomeTab : Tab, ResettableTab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Inicio"
            val icon = rememberVectorPainter(EvaIcons.Fill.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {


        Navigator(HomeScreen) {
            val navigator = LocalNavigator.current
            Log.i("homenav", "${navigator?.items}")
            resetToRootCallback = {
                navigator?.popUntilRoot()
            }
            CurrentScreen()
        }


    }


    private var resetToRootCallback: (() -> Unit)? = null

    override val resetToRoot: () -> Unit
        get() = {

            resetToRootCallback?.invoke()
        }


}
