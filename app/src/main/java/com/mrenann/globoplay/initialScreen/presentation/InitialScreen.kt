package com.mrenann.globoplay.initialScreen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.mrenann.globoplay.homeScreen.presentation.HomeScreen
import com.mrenann.globoplay.myListScreen.MyListScreen

val LocalNavigatorParent = staticCompositionLocalOf<Navigator?> { null }

object InitialScreen : Screen {
    @Composable
    override fun Content() {
        val navigatorParent = LocalNavigator.current

        TabNavigator(
            HomeScreen, tabDisposable = {
                TabDisposable(
                    navigator = it, tabs = listOf(HomeScreen, MyListScreen)
                )
            }

        ) {
            CompositionLocalProvider(LocalNavigatorParent provides navigatorParent) {
                HomeContent()
            }
        }
    }

    @Composable
    fun HomeContent() {
        val tabs = listOf(HomeScreen, MyListScreen)
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigationBar(tabs)
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            PaddingValues(
                                top = 0.dp,
                                start = innerPadding.calculateStartPadding(
                                    layoutDirection = LayoutDirection.Ltr
                                ),
                                bottom = innerPadding.calculateBottomPadding(),
                                end = innerPadding.calculateEndPadding(
                                    layoutDirection = LayoutDirection.Ltr
                                ),
                            )
                        )
                ) {

                    CurrentTab()
                }
            }
        }

    }

    @Composable
    private fun BottomNavigationBar(tabs: List<Tab>) {

        val containerColor = MaterialTheme.colorScheme.primaryContainer

        NavigationBar(
            modifier = Modifier.clip(RoundedCornerShape(20.dp, 20.dp)),
            containerColor = containerColor
        ) {
            val tabNavigator = LocalTabNavigator.current
            tabs.forEachIndexed { index, tab ->
                TabNavigationItem(
                    tab = tab,
                    selected = tabNavigator.current.key == tab.key,
                    onClick = { tabNavigator.current = tab }
                )
            }

        }
    }

    @Composable
    fun RowScope.TabNavigationItem(
        tab: Tab,
        selected: Boolean,
        onClick: () -> Unit
    ) {
        val selectedColor = MaterialTheme.colorScheme.primary
        val unselectedColor = MaterialTheme.colorScheme.onSurface

        NavigationBarItem(
            selected = selected,
            onClick = onClick,
            icon = {
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title,
                    tint = if (selected) selectedColor else unselectedColor
                )
            },
            label = {
                Text(
                    tab.options.title,
                    color = if (selected) selectedColor else unselectedColor
                )
            },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                unselectedIconColor = unselectedColor,
                selectedTextColor = selectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
    }

}