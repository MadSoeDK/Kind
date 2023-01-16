package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.HomeScreens
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

class LoginViewModel(
    val navController: NavController,
    private val auth: AccountServiceImpl = AccountServiceImpl(FirebaseAuth.getInstance()),
    private val storage: StorageServiceImpl = StorageServiceImpl()
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
                storage.updateCurrentUser()
                println("Succesfully logged in $isLoggedIn")
                return@launch
            } catch (e: FirebaseAuthInvalidUserException) {
                formState.fields[0].showError("Could not find user with this email")
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                formState.fields[1].showError("Wrong password")
            } catch (e: Exception) {
                formState.showError("Some error happened. Try again later")
                println("Error login:" + isLoggedIn + e.printStackTrace())
            } finally {
                isLoading = false
                isLoggedIn = false
            }
        }
    }
}