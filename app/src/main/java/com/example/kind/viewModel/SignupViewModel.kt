package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.Charity
import com.example.kind.model.DonationFrequency
import com.example.kind.model.Subscription
import com.example.kind.model.User
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.*
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class SignupViewModel(
    val navController: NavController,
    val navigateAmount: () -> Unit,
    val navigateFreq: () -> Unit,
    val navigateOnUserCreate: () -> Unit,
    val navigateOnPortfolioCreate: () -> Unit,
    private val storage: StorageServiceImpl,
    private val auth: AccountServiceImpl
) : ViewModel() {
    // Charities state
    private val _charityData = MutableStateFlow(listOf<Charity>())
    val charityData: StateFlow<List<Charity>> = _charityData.asStateFlow()

    // User portfolio state
    private var _portfolioState = MutableStateFlow(PortState())
    val portfolioState: StateFlow<PortState> = _portfolioState.asStateFlow()

    var formState by mutableStateOf(FormState())
    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Full name", label = "Full name", validators = listOf(Required())),
        KindTextField(name = "Email", label = "Email", validators = listOf(Required(), Email())),
        KindTextField(name = "Password", label = "Password", validators = listOf(Required(), Password())),
    )

    var isLoading by mutableStateOf(false)
    var amountState by mutableStateOf(0)
    var frequencyState by mutableStateOf(DonationFrequency.Monthly)

    init {
        getCharities()
    }

    fun setFrequency(frequency: DonationFrequency) {
        frequencyState = frequency
        navigateFreq()
    }

    fun setAmount(amount: Int) {
        amountState = amount
        navigateAmount()
    }

    private fun generateUser(): User {
        var monthlyPayment = 0
        monthlyPayment = when (frequencyState) {
            DonationFrequency.Monthly -> {
                amountState
            }
            DonationFrequency.Quarterly -> {
                amountState.div(3)
            }
            DonationFrequency.HalfYearly -> {
                amountState.div(6)
            }
            DonationFrequency.Yearly -> {
                amountState.div(12)
            }
        }
        return User(formState.getData().getOrDefault("Full name", ""), monthlyPayment.toDouble())
    }

    fun onSignUpFormSubmit() {
        isLoading = true
        if (!formState.validate()) {
            isLoading = false
            return
        }
        viewModelScope.launch {
            try {
                val data = formState.getData()
                auth.createUserWithEmailAndPassword(data.getValue("Email"), data.getValue("Password"))
                storage.addUser(User(data.getValue("Full name")))
                isLoading = false
                navigateOnUserCreate()
            } catch (e: FirebaseAuthUserCollisionException) {
                isLoading = false
                formState.fields[1].showError("User already exists")
            } catch (e: Exception) {
                isLoading = false
                println("Could not sign in: " + e.printStackTrace())
            }
        }
    }

    fun onSignupSubmission() {
        viewModelScope.launch {
            try {
                storage.addSubscriptionToUser(_portfolioState.value.subscription)
                navigateOnPortfolioCreate()
            }
            catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    fun getCharities() {
        viewModelScope.launch {
            try {
                _charityData.update {
                    storage.getCharities()
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    fun getCharitiesByCategory(category: String) {
        if(category == "All") {
            getCharities()
        } else {
            viewModelScope.launch {
                try {
                    _charityData.update {
                        storage.getCharitiesByCategory(category)
                    }
                } catch (e: Exception) {
                    println(e.printStackTrace())
                }
            }
        }
    }

    fun updateSelectedCharities(charity: List<Charity>) {
        _charityData.update { charity }
    }

    fun updatePortfolioState() {
        val subs: ArrayList<Subscription> = ArrayList()
        val count = _charityData.value.count { it.inPortfolio }
        println("Count " + count + "Amount " + amountState)
         _charityData.value.map {
            if (it.inPortfolio) {
                subs.add(
                    Subscription(
                        amount = (amountState / count).toDouble(),
                        charityID = it.id,
                        charityName = it.name,
                        initDate = Timestamp.now(),
                        id = it.id
                    )
                )
            }
        }
        _portfolioState.update {
            it.copy(
                subscription = subs
            )
        }
    }
}

