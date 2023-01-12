package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.HomeScreens
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.view.composables.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

class LoginViewModel(
    val navController: NavController,
    private val auth: AccountServiceImpl = AccountServiceImpl(FirebaseAuth.getInstance())
) : ViewModel() {
    var isLoggedIn by mutableStateOf(auth.hasUser)
    var isLoading by mutableStateOf(false)

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(
            name = "Password",
            label = "Password",
            validators = listOf(Required(), Password())
        ),
    )

    fun onAuthentication(data: Map<String, String>) {
        isLoading = true
        if (!formState.validate()) {
            isLoading = false
            return
        }
        viewModelScope.launch {
            try {
                auth.authenticateUser(data.getValue("Email"), data.getValue("Password"))
                isLoading = false
                isLoggedIn = true
                navController.navigate(HomeScreens.Root.route) {
                    popUpTo(HomeScreens.Root.route)
                }
                println("Succesfully logged in $isLoggedIn")
            } catch (e: FirebaseAuthInvalidUserException) {
                formState.showError("Email or password is invalid")
            } catch (e: Exception) {
                println("Error login:" + isLoggedIn + e.printStackTrace())
            } finally {
                isLoading = false
                isLoggedIn = false
            }
        }
    }
}