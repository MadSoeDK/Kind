package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.AuthenticationScreens
import com.example.kind.NavbarScreens
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
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

        navController.navigate(NavbarScreens.Root.route)
    }

    fun signUp() {
        navController.navigate(AuthenticationScreens.About.route)
    }
    fun CoroutineScope.callMethodInCoroutine() {
        storage = StorageServiceImpl()
        launch(Dispatchers.IO) {
            // Call method here
            storage.addUser("mpnvip@gmail.com", "fajosdji")

        }
    }
}