package com.example.kind

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.launch

class AppViewModel(
    val navController: NavController,
    private val auth: AccountServiceImpl,
    private val storage: StorageServiceImpl,
) : ViewModel() {

    fun onLogout() {
        viewModelScope.launch {
            try {
                auth.signOut()
                storage.updateCurrentUser()
                navController.navigate(AuthenticationScreens.Root.route)
                println("Successfully logged out")
            } catch (e: Exception) {
                println("Error logging out" + e.printStackTrace())
            }
        }
        //navController.navigate(AuthenticationScreens.Root.route)
    }

    fun onSignUp(data: Map<String, String>) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(
                    data.getValue("Email"),
                    data.getValue("Password")
                )
                println("New user created")
            } catch (e: Exception) {
                println("Could not sign in: " + e.printStackTrace())
            }
        }
        navController.navigate(AuthenticationScreens.About.route)
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