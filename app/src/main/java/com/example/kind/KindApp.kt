package com.example.kind

import android.app.Application
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.kind.view.auth_screens.AuthenticationScreen
import com.example.kind.view.main_screens.PortfolioScreen
import com.example.kind.viewModel.*
import com.example.kind.view.signup_screens.*
import com.example.kind.view.main_screens.*
import com.example.kind.view.screens.ArticleScreen
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.ExplorerViewModel
import com.example.kind.viewModel.PortfolioViewModel
import com.example.kind.viewModel.ProfileViewModel

sealed class NavbarScreens(val route: String, var icon: ImageVector) {
    object Root : NavbarScreens("root", Icons.Filled.Favorite)
    object Home : NavbarScreens("home", Icons.Filled.Home)
    object Portfolio : NavbarScreens("portfolio", Icons.Filled.Favorite)
    object Explorer : NavbarScreens("explorer", Icons.Filled.Favorite)
    object Profile : NavbarScreens("profile", Icons.Filled.AccountBox)
    object Charity : NavbarScreens("charity", Icons.Filled.Favorite)
    object Article : NavbarScreens("article", Icons.Filled.Favorite)
}

sealed class AuthenticationScreens(val route: String) {
    object Root : AuthenticationScreens("auth_root")
    object Login : AuthenticationScreens("login")
    object About : AuthenticationScreens("about")
    object Authenticate : AuthenticationScreens("authentication")
    object ForgotPassword : AuthenticationScreens("forgot_password")
}

sealed class SignupScreens(val route: String) {
    object Root : SignupScreens("signup_root")
    object Signup : SignupScreens("signup")
    object CreatePortfolio : SignupScreens("create_portfolio")
    object SetAmount : SignupScreens("portfolio_amount")
    object SetFreq : SignupScreens("portfolio_freq")
    object BuildPortfolio : SignupScreens("build_portfolio")
    object Summary : SignupScreens("portfolio_summary")
}

class Global : Application() {
    companion object {
        @JvmField
        var currentUser: String = ""
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KindApp() {
    val viewModel = AppViewModel(navController = rememberNavController())
    NavHost(
        navController = viewModel.navController,
        startDestination = AuthenticationScreens.Root.route
    ) {
        navigation(
            startDestination = NavbarScreens.Home.route,
            route = NavbarScreens.Root.route
        ) {
            composable(NavbarScreens.Home.route) {
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    content = { HomeScreen(HomeViewModel(viewModel.navController)) }
                )
            }
            composable(NavbarScreens.Portfolio.route) {
                val portfolioViewModel = PortfolioViewModel()
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    FloatingActionButton = {
                        FloatingActionButton(
                            onClick = { portfolioViewModel.toggleModal() },
                            containerColor = MaterialTheme.colorScheme.primary,
                            content = {
                                Icon (
                                    Icons.Filled.Edit,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.background,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp),
                                )

                            },
                            shape = RoundedCornerShape(10.dp)
                        )
                    },
                    content = { PortfolioScreen(portfolioViewModel) }
                )
            }
            composable(NavbarScreens.Profile.route) {
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    content = { ProfileScreen(ProfileViewModel()) }
                )
            }
            composable(NavbarScreens.Explorer.route) {
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    content = { ExplorerScreen(ExplorerViewModel(viewModel.navController)) }
                )
            }
            composable(NavbarScreens.Charity.route  + "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { NavBackStackEntry ->
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    content = { CharityScreen(
                        viewModel = CharityViewModel(navController = viewModel.navController, id = NavBackStackEntry.arguments!!.getInt("id", 0)),
                    )}
                )
            }
            composable(NavbarScreens.Article.route  + "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { NavBackStackEntry ->
                Screen(
                    NavigationBar = { KindNavigationBar(viewModel = viewModel) },
                    content = {
                        ArticleScreen(viewModel = ArticleViewModel(navController = viewModel.navController, id = NavBackStackEntry.arguments!!.getInt("id", 0)))
                    }
                )
            }
        }

        navigation (
            startDestination = AuthenticationScreens.Authenticate.route,
            route = AuthenticationScreens.Root.route
        ) {
            composable(route = AuthenticationScreens.Authenticate.route) {
                AuthenticationScreen(
                    navigateToLogin = { viewModel.navigate(AuthenticationScreens.Login.route) },
                    navigateToSignup = { viewModel.navigate(SignupScreens.Root.route) }
                )
            }
            composable(route = AuthenticationScreens.Login.route) {
                LoginScreen(AuthViewModel(viewModel.navController))
            }
            composable(route = AuthenticationScreens.About.route) {
                AboutKindScreen {
                    viewModel.navigate(SignupScreens.Root.route)
                }
            }
        }

        val signupViewModel = SignupViewModel (
            navigateAmount = { viewModel.navigate(SignupScreens.SetFreq.route) },
            navigateFreq = { viewModel.navigate(SignupScreens.BuildPortfolio.route) }
        )

        navigation(
            startDestination = SignupScreens.Signup.route,
            route = SignupScreens.Root.route
        ) {
            composable(route = SignupScreens.Signup.route) {
                PersonalInformationScreen(viewModel = signupViewModel, next = { viewModel.navigate(SignupScreens.CreatePortfolio.route) }) {
                    viewModel.navigate(AuthenticationScreens.Root.route)
                }
            }
            composable(route = SignupScreens.CreatePortfolio.route) {
                SignUpIntroScreen(
                    navigateToPortfolio = { viewModel.navigate(SignupScreens.SetAmount.route) },
                    navigateToHome = { viewModel.navigate(NavbarScreens.Root.route) }
                )
            }
            composable(route = SignupScreens.SetAmount.route){
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(50) }
                DonationAmountScreen(
                    next = { signupViewModel.setAmount(selectedOption) },
                    back = { viewModel.navigate(SignupScreens.CreatePortfolio.route) },
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
            }
            composable(route = SignupScreens.SetFreq.route) {
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(DonationFrequency.Monthly) }
                DonationFreqScreen(
                    next = { signupViewModel.setFrequency(selectedOption) },
                    back = { viewModel.navigate(SignupScreens.SetAmount.route) },
                    selectedOption,
                    onOptionSelected
                )
            }
            composable(route = SignupScreens.BuildPortfolio.route) {
                PortfolioBuilderScreen(
                    navigateToPortfolioBuilder = { viewModel.navigate(SignupScreens.BuildPortfolio.route) },
                    next = { viewModel.navigate(SignupScreens.Summary.route) },
                    back = { viewModel.navigate(SignupScreens.Summary.route) }
                )
            }
            composable(route = SignupScreens.Summary.route) {
                Screen(
                    content = { SummaryScreen(
                        next = { viewModel.navigate(NavbarScreens.Root.route) },
                        back = { viewModel.navigate(SignupScreens.BuildPortfolio.route) }
                    )}
                )
            }
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
    NavigationBar {
        val items = listOf(NavbarScreens.Home, NavbarScreens.Portfolio, NavbarScreens.Explorer, NavbarScreens.Profile)
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
                onClick = { viewModel.navigate(screen.route) },
                colors = NavigationBarItemDefaults.colors(
                    MaterialTheme.colorScheme.surfaceVariant, //logo for valgt
                    MaterialTheme.colorScheme.onSurface, //tekst under valgt
                    MaterialTheme.colorScheme.primary, //knap/omrids
                    MaterialTheme.colorScheme.onSurface, //logo ikke valgt
                    MaterialTheme.colorScheme.onSurface //tekst ikke valgt
                )
            )
        }
    }
}