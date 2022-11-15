package com.example.kind

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.view.screens.PortfolioScreen
import com.example.kind.ViewModel.*
import com.example.kind.view.login.LoginScreen
import com.example.kind.view.login.SignupScreen
import com.example.kind.view.login.SignupViewModel
import com.example.kind.view.login.StartScreen
import com.example.kind.view.screens.*
import com.example.kind.view.theme.Typography

sealed class Screen(val route: String, var icon: ImageVector) {
    object Start : Screen("start", Icons.Filled.Favorite)
    object Login : Screen("login", Icons.Filled.Favorite)
    object Signup : Screen("signup", Icons.Filled.Favorite)
    object Home : Screen("home", Icons.Filled.Home)
    object Portfolio : Screen("portfolio", Icons.Filled.Favorite)
    object Explorer : Screen("explorer", Icons.Filled.Favorite)
    object Profile : Screen("profile", Icons.Filled.AccountBox)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KindApp() {
    val viewModel = AppViewModel(navController = rememberNavController())
    NavHost(
        navController = viewModel.navController,
        startDestination = Screen.Start.route,
    ) {
        composable(Screen.Start.route) {
            Screen(
                content = { StartScreen(navController = viewModel.navController) }
            )
        }
        composable(Screen.Login.route) {
            Screen { LoginScreen() }
        }
        composable(Screen.Signup.route) {
            Screen { SignupScreen(SignupViewModel()) { viewModel.finishSignup() } }
        }
        composable(Screen.Home.route) {
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                content = { HomeScreen(HomeViewModel()) }
            )
        }
        composable(Screen.Portfolio.route) {
            val portfolioViewModel = PortfolioViewModel()
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                FloatingActionButton = { EditPortfolioFAB { portfolioViewModel.toggleModal() } },
                content = { PortfolioScreen(portfolioViewModel) }
            )
        }
        composable(Screen.Profile.route) {
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                content = { ProfileScreen(ProfileViewModel()) }
            )
        }
        composable(Screen.Explorer.route) {
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                content = { ExplorerScreen(ExplorerViewModel()) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    NavigationBar: @Composable () -> Unit = {},
    FloatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { NavigationBar() },
        floatingActionButton = { FloatingActionButton() },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                content(it)
            }
        }
    )
}

@Composable
fun KindNavigationBar(
    viewModel: AppViewModel
) {
    NavigationBar(
        containerColor = Color.White,
    ) {
        val items = listOf(Screen.Home, Screen.Portfolio, Screen.Explorer, Screen.Profile)
        items[1].icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_analytics_24)
        items[2].icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_travel_explore_24)
        val navBackStackEntry by viewModel.navController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.route) },
                selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { viewModel.navigate(screen.route) }
            )
        }
    }
}

@Composable
fun EditPortfolioFAB(
    toggle: () -> Unit,
) {
    FloatingActionButton(
        onClick = toggle,
        content = {
            Icon(
                Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),

                )
        },
        containerColor = Typography.headlineLarge.color,
        shape = RoundedCornerShape(10.dp)
    )
}