package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kind.StripeUtil
import kotlinx.coroutines.launch

class PaymentViewModel(
    val stripeUtil: StripeUtil
) : ViewModel() {

    //var payButtonIsEnabled by mutableStateOf(false)
    val paymentId: String = ""
    val clientSecret: String = ""


    fun test() {
        viewModelScope.launch {
            //payment.createCustomer()
        }
    }

    fun initializeSession() {
        viewModelScope.launch {
            stripeUtil.setupPaymentSession()
        }
    }

    fun createPaymentIntent() {
        viewModelScope.launch {
            stripeUtil.createPaymentIntent(50.0)
            stripeUtil.selectedPayMethod()
        }
    }

}