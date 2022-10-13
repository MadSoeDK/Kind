package com.example.kind.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kind.KindAppState
import com.example.kind.Screen

@Composable
fun KindBottomBar(items: List<Screen>, appState: KindAppState) {
    BottomNavigation {
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(screen.route) },
                selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    appState.navigateToBottomBarRoute(screen.route)
                }
            )
        }
    }
}