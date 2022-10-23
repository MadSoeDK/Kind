package com.example.kind

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.View.home.composables.HomeScreen
import com.example.kind.View.profile.ProfileScreen
import com.example.kind.View.screens.PortfolioScreen
import com.example.kind.ViewModel.ProfileViewModel
import com.example.kind.View.theme.KindTheme
import com.example.kind.ViewModel.PortfolioViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Portfolio : Screen("Portfolio")
    object Profile : Screen("profile")
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Profile)
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val destination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null
                            )
                        },
                        label = { Text(screen.route) },
                        selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        KindNavigation(navController = navController)
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
            HomeScreen(viewModel)
        }
        composable(Screen.Portfolio.route) {
            val viewModel = viewModel<PortfolioViewModel>()
            PortfolioScreen(viewModel)
        }
        composable(
            Screen.Profile.route,
        ) {
            val viewModel = viewModel<ProfileViewModel>()
            ProfileScreen(
                viewModel
            ) { navController.navigate(Screen.Home.route) }
        }
    }
}
