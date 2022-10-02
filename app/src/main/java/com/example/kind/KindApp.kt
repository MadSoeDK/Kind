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
import com.example.kind.ui.home.HomeScreen
import com.example.kind.ui.home.HomeViewModel
import com.example.kind.ui.theme.KindTheme

@Composable
fun KindApp() {
    KindTheme {
        val appState = rememberKindAppState()
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val items = listOf(Screen.Home, Screen.Profile)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            KindBottomBar(items = items, navController = navController)
        }
    ) {
        KindNavigation(navController = navController)
    }
}

@Composable
fun KindBottomBar(items: List<Screen>, navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)},
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

@Composable
fun KindNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                textProvider = { viewModel.getText() },
                navigateToPage = { navController.navigate(Screen.Profile.route) }
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
