package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kind.model.Charity
import com.example.kind.model.User
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class DonationFrequency {
    Monthly,
    Quarterly,
    HalfYearly,
    Yearly
}

data class PortfolioState(
    var frequency: DonationFrequency? = null,
    var amount: Int? = 0,
    var charities: List<Charity>? = null,
)

class SignupViewModel(
    val navigateAmount: () -> Unit,
    val navigateFreq: () -> Unit,
) : ViewModel() {
    private val storage: StorageServiceImpl = StorageServiceImpl()
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
        navigateFreq()
    }

    fun setAmount(amount: Int) {
        portfolioState.update { it.copy(amount = amount) }
        navigateAmount()
    }

    private fun generateUser(): User {
        var monthlyPayment = 0
        monthlyPayment = when (portfolioState.value.frequency) {
            DonationFrequency.Monthly -> {
                portfolioState.value.amount!!
            }
            DonationFrequency.Quarterly -> {
                portfolioState.value.amount!!.div(3)
            }
            DonationFrequency.HalfYearly -> {
                portfolioState.value.amount!!.div(6)
            }
            DonationFrequency.Yearly -> {
                portfolioState.value.amount!!.div(12)
            }
            else -> {
                0
            }
        }
        return User(formState.getData().getValue("Full name"), monthlyPayment)
    }

    fun addDataToUser(){
        viewModelScope.launch {
            try {
                storage.changeUser(generateUser(), storage.getUIDofCurrentUser())
            }
            catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }
}

