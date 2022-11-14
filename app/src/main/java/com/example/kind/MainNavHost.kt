package com.example.kind

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.view.home.composables.ExplorerScreen
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.view.home.composables.HomeScreen
import com.example.kind.view.profile.ProfileScreen
import com.example.kind.view.screens.PortfolioScreen
import com.example.kind.ViewModel.ExplorerViewModel
import com.example.kind.ViewModel.ProfileViewModel
import com.example.kind.ViewModel.PortfolioViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Portfolio : Screen("Portfolio")
    object Explorer : Screen("explorer")
    object Profile : Screen("profile")
    object Organization : Screen("Organization")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Portfolio, Screen.Explorer, Screen.Profile)
    Scaffold(
        bottomBar = {
            NavigationBar (
                containerColor = Color.White,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val destination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = screen.route) },
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
        Column (
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(
                    top = it.calculateTopPadding(),
                    right = it.calculateRightPadding(LayoutDirection.Rtl),
                    bottom = it.calculateBottomPadding(),
                    left = it.calculateLeftPadding(LayoutDirection.Ltr)
                )
                .verticalScroll(rememberScrollState())
        ) {
            //TODO: Global padding values of all screens
            KindNavigation(navController = navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
        composable(Screen.Profile.route) {
            val viewModel = viewModel<ProfileViewModel>()
            ProfileScreen(
                viewModel
            )
        }
        composable(Screen.Explorer.route) {
            val viewModel = viewModel<ExplorerViewModel>();
            ExplorerScreen(viewModel = viewModel)
        }

        // Organization
        composable(Screen.Organization.route) {
            val viewModel = viewModel<ExplorerViewModel>();
            ExplorerScreen(viewModel = viewModel)
        }
    }
}
