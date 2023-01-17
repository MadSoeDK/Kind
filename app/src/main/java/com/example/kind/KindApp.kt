package com.example.kind

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.auth_screens.AuthenticationScreen
import com.example.kind.view.auth_screens.ForgotPasswordScreen
import com.example.kind.view.auth_screens.LoginScreen
import com.example.kind.view.main_screens.PortfolioScreen
import com.example.kind.viewModel.*
import com.example.kind.view.signup_screens.*
import com.example.kind.view.main_screens.*
import com.example.kind.view.main_screens.ArticleScreen
import com.example.kind.viewModel.ExplorerViewModel
import com.example.kind.viewModel.PortfolioViewModel
import com.example.kind.viewModel.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

sealed class HomeScreens(val route: String, var icon: ImageVector) {
    object Root : HomeScreens("root", Icons.Filled.Favorite)
    object Home : HomeScreens("home", Icons.Filled.Home)
    object Portfolio : HomeScreens("portfolio", Icons.Filled.Favorite)
    object Explorer : HomeScreens("explorer", Icons.Filled.Favorite)
    object Profile : HomeScreens("profile", Icons.Filled.AccountBox)
    object Charity : HomeScreens("charity", Icons.Filled.Favorite)
    object Article : HomeScreens("article", Icons.Filled.Favorite)
    object Payment : HomeScreens("payment", Icons.Filled.Favorite)
    object TransactionHistory : HomeScreens("transaction_history", Icons.Filled.Favorite)
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
    object Charity : SignupScreens("signup_charity")
    object BuildPortfolio : SignupScreens("build_portfolio")
    object Summary : SignupScreens("portfolio_summary")
}

@Composable
fun KindApp(
    paymentViewModel: PaymentViewModel,
    storage: StorageServiceImpl,
    auth: AccountServiceImpl
) {
    val navController = rememberNavController()
    val appViewModel = AppViewModel(navController, auth, storage)
    paymentViewModel.navigateOnPaymentSuccess = { navController.navigate(HomeScreens.Explorer.route) }
    NavHost(
        navController = navController,
        startDestination = if (Firebase.auth.currentUser != null) HomeScreens.Root.route else AuthenticationScreens.Root.route
    ) {
        homeNavGraph(navController, appViewModel, paymentViewModel, storage)
        authNavGraph(navController, auth, storage)
        signupNavGraph(navController, storage, auth)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    appViewModel: AppViewModel,
    paymentViewModel: PaymentViewModel,
    storage: StorageServiceImpl
) {
    navigation(
        startDestination = HomeScreens.Home.route,
        route = HomeScreens.Root.route
    ) {
        val homeViewModel = HomeViewModel(navController, storage)
        val portfolioViewModel = PortfolioViewModel(storage) { appViewModel.navController.navigate(HomeScreens.Explorer.route) }
        val explorerViewModel = ExplorerViewModel(navController, storage)
        val profileViewModel = ProfileViewModel(storage)

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
            ) { ProfileScreen(
                viewModel = profileViewModel,
                onLogout = { appViewModel.onLogout() },
                transactionHistory = {navController.navigate(HomeScreens.TransactionHistory.route)} ) }
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
                modifier = Modifier.fillMaxSize(),
                NavigationBar = { KindNavigationBar(navController) },
                content = {
                    CharityScreen(
                        viewModel = CharityViewModel(
                            navController = navController,
                            id = NavBackStackEntry.arguments!!.getString("id", ""),
                            onAddToPortfolio = { portfolioViewModel.getSubscriptions() },
                            charities = portfolioViewModel.data.collectAsState(),
                            storage = storage
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
                            id = NavBackStackEntry.arguments!!.getString("id", ""),
                            storage = storage
                        )
                    )
                }
            )
        }
        composable(
            HomeScreens.Payment.route + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            PaymentScreen(viewModel = paymentViewModel, it.arguments!!.getString("name", ""))
        }

        composable(HomeScreens.Profile.route) {
            Screen(
                NavigationBar = { KindNavigationBar(navController) }
            ) { ProfileScreen(viewModel = profileViewModel, onLogout = {appViewModel.onLogout()}, transactionHistory = {navController.navigate(HomeScreens.TransactionHistory.route)} ) }
        }

        composable(HomeScreens.TransactionHistory.route) {
            val transactionHistoryViewModel = TransactionHistoryViewModel(storage = storage, navController = navController)
            Screen{
            TransactionHistoryScreen(viewModel = transactionHistoryViewModel, back = {navController.navigate(HomeScreens.Profile.route)})}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.signupNavGraph(
    navController: NavController,
    storage: StorageServiceImpl,
    auth: AccountServiceImpl
) {
    val signupViewModel = SignupViewModel(
        navigateAmount = { navController.navigate(SignupScreens.SetFreq.route) },
        navigateFreq = { navController.navigate(SignupScreens.BuildPortfolio.route) },
        navController = navController,
        storage = storage,
        auth = auth,
        navigateOnUserCreate = { navController.navigate(SignupScreens.CreatePortfolio.route) { popUpTo(SignupScreens.CreatePortfolio.route) } }
    )
    navigation(
        startDestination = SignupScreens.Signup.route,
        route = SignupScreens.Root.route
    ) {
        composable(route = SignupScreens.Signup.route) {
           Screen (
               signupNavigation = {
                   SignupNavigation(
                       next = { signupViewModel.onSignUpFormSubmit() },
                       back = { navController.popBackStack() }
                   )
               },
               content = {
                    PersonalInformationScreen (
                        viewModel = signupViewModel,
                    )
                }
            )
        }

        composable(route = SignupScreens.CreatePortfolio.route) {
            SignUpIntroScreen(
                navigateToPortfolio = { navController.navigate(SignupScreens.SetAmount.route) },
                navigateToHome = { navController.navigate(SignupScreens.Summary.route) }
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
                viewModel = signupViewModel,
                navigateToPortfolioBuilder = { navController.navigate(SignupScreens.BuildPortfolio.route) },
                next = { navController.navigate(SignupScreens.Summary.route) },
                back = { navController.navigate(SignupScreens.SetFreq.route) }
            )
        }

        composable(
            SignupScreens.Charity.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { NavBackStackEntry ->
            Screen(
                content = {
                    CharityScreen(
                        viewModel = CharityViewModel(
                            navController = navController,
                            id = NavBackStackEntry.arguments!!.getString("id", ""),
                            onAddToPortfolio = {  },
                            charities = signupViewModel.portfolioData.collectAsState(),
                            storage = storage
                        ),
                    )
                }
            )
        }

        composable(route = SignupScreens.Summary.route) {
            Screen(content = {
                SummaryScreen(
                    viewModel = signupViewModel,
                    next = { navController.navigate(HomeScreens.Root.route) },
                    back = { navController.navigate(SignupScreens.BuildPortfolio.route) }
                )
            })
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    auth: AccountServiceImpl,
    storage: StorageServiceImpl
) {
    val loginViewModel = LoginViewModel(navController, auth = auth, storage = storage)
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
            LoginScreen(loginViewModel
            ) { navController.navigate(AuthenticationScreens.ForgotPassword.route) }
        }
        composable(route = AuthenticationScreens.About.route) {
            AboutKindScreen { navController.navigate(SignupScreens.CreatePortfolio.route) }
        }
        composable(route = AuthenticationScreens.ForgotPassword.route) {
            ForgotPasswordScreen(loginViewModel = loginViewModel) { navController.navigate(AuthenticationScreens.Login.route) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen (
    modifier: Modifier = Modifier,
    NavigationBar: @Composable () -> Unit = {},
    FloatingActionButton: @Composable () -> Unit = {},
    signupNavigation: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold (
        bottomBar = NavigationBar,
        floatingActionButton = FloatingActionButton
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content(it)
            signupNavigation()
        }
    }
}

@Composable
fun SignupNavigation (
    next: () -> Unit = {},
    back: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 15.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp).width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = back) {
                Text("← Back")
            }
            Button(onClick = next ) {
                Text("Next →")
            }
        }
    }
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