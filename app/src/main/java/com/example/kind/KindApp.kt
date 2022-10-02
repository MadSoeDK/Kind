package com.example.kind

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun KindApp(
    appState: KindAppState = rememberKindAppState()
) {
    Navigation()
}

@Composable
fun Navigation() {
    val items = listOf(Screen.Home, Screen.Profile)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onNavigateToProfile = { navController.navigate(Screen.Profile.route) })
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onNavigateToHome = { navController.navigate(Screen.Home.route) })
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit
) {
    Button(onClick = onNavigateToProfile) {
        Text(text = "Home screen")
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
