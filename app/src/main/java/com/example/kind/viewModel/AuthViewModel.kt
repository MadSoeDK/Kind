package com.example.kind.viewModel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.AuthenticationScreens
import com.example.kind.NavbarScreens
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class AuthViewModel (
    private val navController: NavController,
    private val auth : AccountServiceImpl
) : ViewModel() {
    lateinit var storage : StorageServiceImpl

    var isLoggedIn by mutableStateOf(auth.hasUser)
    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf (
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
    )

    fun onAuthentication(data: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.authenticateUser(data.getValue("Email"), data.getValue("Password"))
                isLoggedIn = true
                println("Succesfully logged in")
            } catch (e: Exception) {
                isLoggedIn = false
                println("Error login:" + e.printStackTrace())
            }
        }
        navController.navigate(NavbarScreens.Root.route)
        println(auth.hasUser)
    }

    fun signUp() {
        navController.navigate(AuthenticationScreens.About.route)
    }

    fun onLogout() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signOut()
                isLoggedIn = false
                println("Successfully logged out")
            } catch (e: Exception) {
                println("Error logged in" + e.printStackTrace())
            }
        }
        navController.navigate(AuthenticationScreens.Authenticate.route)
    }

    /*
    fun CoroutineScope.callMethodInCoroutine() {
        storage = StorageServiceImpl()
        launch(Dispatchers.IO) {
            // Call method here
        }
    }
    */
}