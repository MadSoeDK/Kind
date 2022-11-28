package com.example.kind

import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

open class AppViewModel (
    val navController: NavHostController
) : ViewModel() {

    fun finishSignup() {
        // TODO: save data or something
        navController.navigate(Screen.Home.route)
    }

    fun navigate(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
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
    }
}