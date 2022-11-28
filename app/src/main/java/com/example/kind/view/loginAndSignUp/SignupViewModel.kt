package com.example.kind.view.loginAndSignUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kind.model.Charity
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

enum class DonationFrequency {
    Monthly,
    Quarterly,
    HalfYearly,
    Yearly
}

data class PortfolioState (
    var frequency: DonationFrequency? = null,
    var amount: Int? = 0,
    var charities: List<Charity>? = null,
)

class SignupViewModel : ViewModel() {
    var steps = mutableStateOf(0)

    private var portfolioState = MutableStateFlow(PortfolioState())

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required())),
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required())),
        KindTextField(
            name = "Repeat password",
            label = "Repeat password",
            validators = listOf(Required())
        ),
    )

    fun onFormSubmit() {
        if (formState.validate()) {
            // TODO: Do something on form submission
        }
        //TODO: Add alert for user
        println("Form submission error!")
    }

    fun setFrequency(frequency: DonationFrequency) {
        portfolioState.update { it.copy(frequency = frequency) }
        steps.value += 1
    }

    fun setAmount(amount: Int) {
        portfolioState.update { it.copy(amount = amount) }
        steps.value += 1
    }

}

