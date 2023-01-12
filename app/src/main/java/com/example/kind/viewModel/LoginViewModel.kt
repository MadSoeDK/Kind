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
import kotlinx.coroutines.launch

class LoginViewModel(
    val navController: NavController,
    private val auth : AccountServiceImpl = AccountServiceImpl(FirebaseAuth.getInstance())
) : ViewModel() {

    var loggedIn by mutableStateOf(auth.hasUser)

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf (
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required(), Password())),
    )

    fun onAuthentication(data: Map<String, String>) {
        if (!formState.validate()) {
            return
        }

        viewModelScope.launch {
            try {
                auth.authenticateUser(data.getValue("Email"), data.getValue("Password"))
                println("Succesfully logged in $loggedIn")
            } catch (e: Exception) {
                println("Error login:" + loggedIn + e.printStackTrace())
            }
        }
        navController.navigate(HomeScreens.Root.route)
    }
}