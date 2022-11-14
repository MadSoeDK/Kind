package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required

class ProfileViewModel : ViewModel() {

    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required())),
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email()))
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