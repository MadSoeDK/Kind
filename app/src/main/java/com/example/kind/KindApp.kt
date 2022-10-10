package com.example.kind

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kind.ui.home.HomeScreen
import com.example.kind.ui.home.HomeViewModel
import com.example.kind.ui.theme.KindTheme
import data.News
import data.User

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
fun KindBottomBar(items: List<Screen>, appState: KindAppState) {
    BottomNavigation {
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)},
                label = { Text(screen.route) },
                selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    appState.navigateToBottomBarRoute(screen.route)
                }
            )
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
                donatedAmountProvider =  viewModel.getDonatedAmount(),
                welcomeText = viewModel.getText(),
                articles = viewModel.getArticles()
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onNavigateToHome = { navController.navigate(Screen.Home.route) })
        }
    }
}

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit
) {
    Button(onClick = onNavigateToHome) {
        Text(text = "Profile screen")
    }
}
