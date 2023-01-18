package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.*
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class ProfileState(
    var email: String,
    var password: String,
    var monthlyPayment: Double,
    var paymentMethod: String = "mobilepay"
)

class ProfileViewModel(
    val storage: StorageServiceImpl,
    val auth: AccountServiceImpl,
    val navigateOnDeleteUser: () -> Unit,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var fieldReadOnly by mutableStateOf(true)

    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf(
        KindTextField(
            name = "Email",
            label = "Email",
            validators = listOf(Required(), Email()),
            readOnly = true,
        ),
        KindTextField(
            name = "Password",
            label = "Password",
            validators = listOf(Required(), Password()),
            readOnly = true
        ),
    )

    fun onFormSubmit() {
        isLoading = true
        if (!formState.validate()) {
            isLoading = false
            return
        }
        viewModelScope.launch {
            try {
                storage.updateUser(
                    formState.getData().getValue("Email"),
                    formState.getData().getValue("Password"),
                )
                isLoading = false
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                isLoading = false
                formState.showError("Invalided email or password")
                e.printStackTrace()
            } catch (e: FirebaseAuthRecentLoginRequiredException) {
                isLoading = false
                formState.showError("An error happened")
                e.printStackTrace()
                Firebase.auth.currentUser!!.reauthenticate(
                    EmailAuthProvider.getCredential(
                        Firebase.auth.currentUser?.email.toString(),
                        formState.getData().getValue("Password")
                    )
                ).await()
                onFormSubmit()
            }
        }
    }

    fun toggleFieldsReadState() {
        formState.fields.forEach { it.readOnlyState = !it.readOnlyState }
    }


    fun onDeleteUser(password: String) {
        viewModelScope.launch {
            try {
                storage.deleteUser(
                    formState.getData().getValue("Password"),
                )
            } catch (e: FirebaseAuthRecentLoginRequiredException) {
                auth.reAuthentication(Firebase.auth.currentUser?.email!!, password)
                e.printStackTrace()
                return@launch
            } catch (e: Exception) {
                e.printStackTrace()
            }
            navigateOnDeleteUser()
        }
    }
}