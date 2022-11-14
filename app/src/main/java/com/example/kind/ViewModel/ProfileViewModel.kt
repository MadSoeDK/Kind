package com.example.kind.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.View.composables.Email
import com.example.kind.View.composables.FormState
import com.example.kind.View.composables.KindTextField
import com.example.kind.View.composables.Required

class ProfileViewModel : ViewModel() {

    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf (
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required())),
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

    fun getButtonText(): String {
        return "Button text";
    }
}