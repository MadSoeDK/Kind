package com.example.kind

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
}

class KindAppState(
    private val context: Context,
    val scaffoldState: ScaffoldState,
    val navController: NavHostController
) {

    fun navigateToBottomBarRoute(route: String) {

    }

}

@Composable
fun rememberKindAppState (
    context: Context = LocalContext.current,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController()
) = remember (context, scaffoldState, navController) {
    KindAppState(context, scaffoldState, navController)
}