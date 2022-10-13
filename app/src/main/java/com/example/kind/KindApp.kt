package com.example.kind

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kind.ui.composables.KindBottomBar
import com.example.kind.ui.home.HomeViewModel
import com.example.kind.ui.home.composables.HomeScreen
import com.example.kind.ui.profile.ProfileScreen
import com.example.kind.ui.theme.KindTheme

@Composable
fun KindApp() {
    KindTheme {
        val appState = rememberKindAppState()
        val items = listOf(Screen.Home, Screen.Profile)
        Scaffold(
            bottomBar = {
                KindBottomBar(items = items, appState = appState )
            }
        ) {
            KindNavigation(navController = appState.navController)
        }
    }
}

@Composable
fun KindNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onNavigateToHome = { navController.navigate(Screen.Home.route) })
        }
    }
}
