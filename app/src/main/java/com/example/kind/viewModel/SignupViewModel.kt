package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.AuthenticationScreens
import com.example.kind.HomeScreens
import com.example.kind.SignupScreens
import com.example.kind.model.Charity
import com.example.kind.model.User
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.Email
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
    val navController: NavController,
    val navigateAmount: () -> Unit,
    val navigateFreq: () -> Unit,
) : ViewModel() {
    private val storage: StorageServiceImpl = StorageServiceImpl()
    private val auth: AccountServiceImpl = AccountServiceImpl()
    private var portfolioState = MutableStateFlow(PortfolioState())
    var userIsCreated by mutableStateOf(true)
    private val _charityData =
        MutableStateFlow(listOf<Charity>())
    val charityData: StateFlow<List<Charity>> = _charityData.asStateFlow()
    private val _portfolioData = MutableStateFlow(PortState())
    val portfolioData: StateFlow<PortState> = _portfolioData.asStateFlow()
    var charityList: List<Charity> = mutableListOf()

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required()), readOnly = false),
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email()), readOnly = false),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required()), readOnly = false),
        KindTextField(
            name = "Repeat password",
            label = "Repeat password",
            validators = listOf(Required()),
            readOnly = false
        ),
    )

    init {
        getCharities()
    }

    fun onFormSubmit(data: Map<String, String>) {
        if (!formState.validate()) {
            return
        }
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

    fun onSignUp(data: Map<String, String>) {
        viewModelScope.launch {
            try {
                userIsCreated = false
                auth.createUserWithEmailAndPassword(
                    data.getValue("Email"),
                    data.getValue("Password")
                )
                auth.authenticateUser(
                    data.getValue("Email"),
                    data.getValue("Password")
                )
                println("New user created")
                userIsCreated = true
                navController.navigate(AuthenticationScreens.About.route) {
                    popUpTo(AuthenticationScreens.About.route)
                }
            } catch (e: Exception) {
                println("Could not sign in: " + e.printStackTrace())
            }
        }
    }

    fun addDataToUser(){
        viewModelScope.launch {
            try {
                storage.updateCurrentUser()
                storage.changeUser(generateUser(), storage.getUIDofCurrentUser())
            }
            catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    fun getCharities(): List<Charity> {
        viewModelScope.launch {
            try {
                _charityData.update {
                    storage.getCharities()
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        return charityList
    }
}

