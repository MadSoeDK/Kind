package com.example.kind.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required

class SignUpPersonalInformationViewModel : ViewModel() {

    //TODO need better naming
    var signUpStepDescription = "Start building your portfolio of kindness today"
    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required())),
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
        KindTextField(name = "Repeat password", label = "Repeat password", validators = listOf(Required())),
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
