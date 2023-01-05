package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    lateinit var storage : StorageServiceImpl
    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
        KindTextField(name = "Monthly payment", label = "Monthly Payment", validators = listOf(Required())),
        KindTextField(name = "Update payment", label = "Update payment", validators = listOf(Required())),
        KindTextField(name = "Payment method", label = "Payment method", validators = listOf(Required())),
        KindTextField(name = "Update payment method", label = "Update payment method", validators = listOf(Required()))
    )

    fun onFormSubmit() {
        if(formState.validate()) {
            // TODO: Do something on form submission
        }
        //TODO: Add alert for user
        println("Form submission error!")
    }

    fun deleteUser() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        coroutineScope.deleteUser()
    }

    fun CoroutineScope.deleteUser() {
        storage = StorageServiceImpl()
        launch(Dispatchers.IO) {
            // Call method here
            storage.deleteUser("e1onBFGdGk1PVwSsBh9M")
        }
    }

    fun getButtonText(): String {
        return "Button text";
    }
}