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
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.view.screens.PortfolioScreen
import com.example.kind.viewModel.*
import com.example.kind.view.loginAndSignUp.*
import com.example.kind.view.screens.*
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.ExplorerViewModel
import com.example.kind.viewModel.PortfolioViewModel
import com.example.kind.viewModel.ProfileViewModel

sealed class Screen(val route: String, var icon: ImageVector) {
    object Home : Screen("home", Icons.Filled.Home)
    object Portfolio : Screen("portfolio", Icons.Filled.Favorite)
    object Explorer : Screen("explorer", Icons.Filled.Favorite)
    object Profile : Screen("profile", Icons.Filled.AccountBox)
    object Charity : Screen("charity", Icons.Filled.Favorite)
}

sealed class AuthenticationDirections(val route: String) {
    object Root : AuthenticationDirections("root")
    object Login : AuthenticationDirections("login")
    object Signup : AuthenticationDirections("signup")
    object Authenticate : AuthenticationDirections("authentication")
    object ForgotPassword : AuthenticationDirections("forgot_password")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KindApp() {
    val viewModel = AppViewModel(navController = rememberNavController())
    NavHost(
        navController = viewModel.navController,
        startDestination = AuthenticationDirections.Root.route//Screen.Home.route,
    ) {
        //er ikke sikker på om vi skal have denne route, siden at start altid er ved login. beholder den for nu in case vi vil have en first time login besked eller lign.
        /* composable(Screen.Start.route) {
            Screen(
                content = { StartScreen(navController = viewModel.navController) }
            )
        }*/
        /*composable(Screen.Login.route) {
            Screen ( content = { LoginScreen(LoginViewModel(viewModel.navController)) })
        }
        composable(Screen.Signup.route) {
            Screen { SignupScreen(SignupViewModel(), finishSignup = { viewModel.finishSignup() }, back = { viewModel.navigate("login") })  }
        }*/

        composable(Screen.Home.route) {
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                content = { HomeScreen(HomeViewModel(viewModel.navController)) }
            )
        }

        composable(Screen.Portfolio.route) {
            val portfolioViewModel = PortfolioViewModel()
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                FloatingActionButton = {
                    FloatingActionButton(
                        onClick = { portfolioViewModel.toggleModal() },
                        content = {
                            Icon (
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
                },
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
                content = { ExplorerScreen(ExplorerViewModel(viewModel.navController)) }
            )
        }

        composable(Screen.Charity.route  + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { NavBackStackEntry ->
            Screen(
                NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                content = { CharityScreen(
                    viewModel = CharityViewModel(navController = viewModel.navController, id = NavBackStackEntry.arguments!!.getInt("id", 0)),
                )}
            )
        }

        navigation (
            startDestination = AuthenticationDirections.Authenticate.route,
            route = AuthenticationDirections.Root.route
        ) {

            composable(route = AuthenticationDirections.Authenticate.route) {
                Authentication(viewModel.navController)
            }
            composable(route = AuthenticationDirections.Login.route) {
                Screen ( content = { LoginScreen(LoginViewModel(viewModel.navController)) })
            }
            composable(route = AuthenticationDirections.Signup.route) {
                Screen { SignupScreen(SignupViewModel(), finishSignup = { viewModel.finishSignup() }, back = { viewModel.navigate("login") })  }
            }
        }
    }
}

@Composable
fun Authentication(navController: NavController) {
    Text("Hello world")
    Button(onClick = { navController.navigate(AuthenticationDirections.Login.route) }) {
        Text(text = "To login")
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