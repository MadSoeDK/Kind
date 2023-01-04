package com.example.kind.view.loginAndSignUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Screen
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val navController: NavController
) : ViewModel() {
    lateinit var storage : StorageServiceImpl

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf (
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
    )

    fun login(data: Map<String, String>) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        coroutineScope.callMethodInCoroutine()

        navController.navigate(Screen.Home.route)
    }

    fun signUp() {
        navController.navigate(Screen.Signup.route)
    }
    fun CoroutineScope.callMethodInCoroutine() {
        storage = StorageServiceImpl()
        launch(Dispatchers.IO) {
            // Call method here
            storage.addUser("mpnvip@gmail.com", "fajosdji")
            storage.deleteUser("wLb0SmIObjH9VVSxbzOL")
        }
    }
}