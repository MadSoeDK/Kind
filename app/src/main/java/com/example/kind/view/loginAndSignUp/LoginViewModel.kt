package com.example.kind.view.loginAndSignUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Screen
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required

data class LoginUiState(
    val email: String = "",
    val password: String = ""
)

class LoginViewModel(
    private val navController: NavController
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
    )

    fun login(data: Map<String, String>) {
        //data.get()
        navController.navigate(Screen.Home.route)
    }

    fun signUp() {
        navController.navigate(Screen.Signup.route)
    }
}