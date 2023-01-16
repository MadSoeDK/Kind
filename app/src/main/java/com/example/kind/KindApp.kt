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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.view.auth_screens.AuthenticationScreen
import com.example.kind.view.auth_screens.LoginScreen
import com.example.kind.view.main_screens.PortfolioScreen
import com.example.kind.viewModel.*
import com.example.kind.view.signup_screens.*
import com.example.kind.view.main_screens.*
import com.example.kind.view.main_screens.ArticleScreen
import com.example.kind.viewModel.ExplorerViewModel
import com.example.kind.viewModel.PortfolioViewModel
import com.example.kind.viewModel.ProfileViewModel
import java.util.*

sealed class HomeScreens(val route: String, var icon: ImageVector) {
    object Root : HomeScreens("root", Icons.Filled.Favorite)
    object Home : HomeScreens("home", Icons.Filled.Home)
    object Portfolio : HomeScreens("portfolio", Icons.Filled.Favorite)
    object Explorer : HomeScreens("explorer", Icons.Filled.Favorite)
    object Profile : HomeScreens("profile", Icons.Filled.AccountBox)
    object Charity : HomeScreens("charity", Icons.Filled.Favorite)
    object Article : HomeScreens("article", Icons.Filled.Favorite)
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

@Composable
fun KindApp() {
    val navController = rememberNavController()
    val appViewModel = AppViewModel(navController)
    NavHost(
        navController = navController,
        startDestination = if (appViewModel.loggedIn) HomeScreens.Root.route else AuthenticationScreens.Root.route
    ) {
        homeNavGraph(navController, appViewModel)
        authNavGraph(navController)
        signupNavGraph(navController, appViewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    appViewModel: AppViewModel
) {
    val homeViewModel = HomeViewModel(navController)
    val portfolioViewModel = PortfolioViewModel()
    val explorerViewModel = ExplorerViewModel(navController)
    val profileViewModel = ProfileViewModel()

    navigation(
        startDestination = HomeScreens.Home.route,
        route = HomeScreens.Root.route
    ) {
        composable(HomeScreens.Home.route) {
            Screen(
                NavigationBar = { KindNavigationBar(navController) },
                content = { HomeScreen(homeViewModel) }
            )
        }
        composable(HomeScreens.Portfolio.route) {
            Screen(
                NavigationBar = { KindNavigationBar(navController) },
                FloatingActionButton = {
                    FloatingActionButton(
                        onClick = { portfolioViewModel.toggleModal() },
                        content = {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
                },
                content = { PortfolioScreen(portfolioViewModel) }
            )
        }
        composable(HomeScreens.Profile.route) {
            Screen(
                NavigationBar = { KindNavigationBar(navController) }
            ) { ProfileScreen(profileViewModel) { appViewModel.onLogout() } }
        }
        composable(HomeScreens.Explorer.route) {
            Screen(
                NavigationBar = { KindNavigationBar(navController) },
                content = { ExplorerScreen(explorerViewModel) }
            )
        }
        composable(
            HomeScreens.Charity.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { NavBackStackEntry ->
            Screen(
                NavigationBar = { KindNavigationBar(navController) },
                content = {
                    CharityScreen(
                        viewModel = CharityViewModel(
                            navController = navController,
                            id = NavBackStackEntry.arguments!!.getString("id", ""),
                            onAddToPortfolio = { portfolioViewModel.getSubscriptions() },
                            charities = portfolioViewModel.data.collectAsState()
                        ),
                    )
                }
            )
        }
        composable(
            HomeScreens.Article.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { NavBackStackEntry ->
            Screen(
                NavigationBar = { KindNavigationBar(navController) },
                content = {
                    ArticleScreen(
                        viewModel = ArticleViewModel(
                            navController = navController,
                            id = NavBackStackEntry.arguments!!.getString("id", "")
                        )
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.signupNavGraph(
    navController: NavController,
    appViewModel: AppViewModel
) {
    val signupViewModel = SignupViewModel(
        navigateAmount = { navController.navigate(SignupScreens.SetFreq.route) },
        navigateFreq = { navController.navigate(SignupScreens.BuildPortfolio.route) }
    )
    navigation(
        startDestination = SignupScreens.Signup.route,
        route = SignupScreens.Root.route
    ) {
        composable(route = SignupScreens.Signup.route) {
            Screen(
                content = {
                    PersonalInformationScreen(
                        viewModel = signupViewModel,
                        next = { navController.navigate(SignupScreens.CreatePortfolio.route) },
                        appViewModel = appViewModel
                    ) {
                        navController.navigate(AuthenticationScreens.Root.route)
                    }
                }
            )
        }

        composable(route = SignupScreens.CreatePortfolio.route) {
            SignUpIntroScreen(
                navigateToPortfolio = { navController.navigate(SignupScreens.SetAmount.route) },
                navigateToHome = { navController.navigate(HomeScreens.Root.route) }
            )
        }

        composable(route = SignupScreens.SetAmount.route) {
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(50) }
            DonationAmountScreen(
                next = { signupViewModel.setAmount(selectedOption) },
                back = { navController.navigate(SignupScreens.CreatePortfolio.route) },
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }

        composable(route = SignupScreens.SetFreq.route) {
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(DonationFrequency.Monthly) }
            DonationFreqScreen(
                next = { signupViewModel.setFrequency(selectedOption) },
                back = { navController.navigate(SignupScreens.SetAmount.route) },
                selectedOption,
                onOptionSelected
            )
        }

        composable(route = SignupScreens.BuildPortfolio.route) {
            PortfolioBuilderScreen(
                navigateToPortfolioBuilder = { navController.navigate(SignupScreens.BuildPortfolio.route) },
                next = { navController.navigate(SignupScreens.Summary.route) },
                back = { navController.navigate(SignupScreens.Summary.route) }
            )
        }

        composable(route = SignupScreens.Summary.route) {
            Screen(content = {
                SummaryScreen(
                    next = { navController.navigate(HomeScreens.Root.route) },
                    back = { navController.navigate(SignupScreens.BuildPortfolio.route) }
                )
            })
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = AuthenticationScreens.Authenticate.route,
        route = AuthenticationScreens.Root.route
    ) {
        composable(route = AuthenticationScreens.Authenticate.route) {
            AuthenticationScreen(
                navigateToLogin = { navController.navigate(AuthenticationScreens.Login.route) },
                navigateToSignup = { navController.navigate(SignupScreens.Root.route) }
            )
        }
        composable(route = AuthenticationScreens.Login.route) {
            LoginScreen(LoginViewModel(navController))
        }
        composable(route = AuthenticationScreens.About.route) {
            AboutKindScreen { navController.navigate(SignupScreens.CreatePortfolio.route) }
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
fun SigninScreen(

) {

}

@Composable
fun KindNavigationBar(
    navController: NavController
) {
    NavigationBar {
        val items = listOf(
            HomeScreens.Home,
            HomeScreens.Portfolio,
            HomeScreens.Explorer,
            HomeScreens.Profile
        )
        items[1].icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_analytics_24)
        items[2].icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_travel_explore_24)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.route.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }) },
                selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { navController.navigate(screen.route) {
                    restoreState = true
                } },
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