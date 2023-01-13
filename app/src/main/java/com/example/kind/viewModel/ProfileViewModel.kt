package com.example.kind.viewModel

import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class ProfileState(
    var email: String,
    var password: String,
    var monthlyPayment: Double,
    var paymentMethod: String = "mobilepay"
)

class ProfileViewModel : ViewModel() {
    var storage: StorageServiceImpl = StorageServiceImpl()
    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf(
        KindTextField(
            name = "Email",
            label = "Email",
            validators = listOf(Required(), Email()),
            readOnly = true
        ),
        KindTextField(
            name = "Password",
            label = "Password",
            validators = listOf(Required(), Password()),
            readOnly = true
        ),
        KindTextField(
            name = "Confirm Email",
            label = "Confirm Email",
            validators = listOf(Required(), Email()),
            readOnly = false
        ),
        KindTextField(
            name = "Confirm Password",
            label = "Confirm Password",
            validators = listOf(Required(), Password()),
            readOnly = false
        )

    )

    fun onFormSubmit() {
        if (formState.validate()) {
            // TODO: Do something on form submission
        }
        //TODO: Add alert for user
        println("Form submission error!")
    }

    fun updateChanges() {
        formState.fields.forEach { it.readOnlyState = !it.readOnlyState }
    }

    fun saveChanges() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        coroutineScope.saveChanges()
    }

    fun CoroutineScope.saveChanges() {
        launch(Dispatchers.IO) {
            // Call method here
            storage.updateUser(formState.fields.get(0).text, formState.fields.get(1).text)
        }
    }

    fun deleteUser() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        coroutineScope.deleteUser()
    }

    fun CoroutineScope.deleteUser() {
        launch(Dispatchers.IO) {
            // Call method here
            storage.deleteUser(formState.fields.get(0).text, formState.fields.get(1).text)
        }
    }
}