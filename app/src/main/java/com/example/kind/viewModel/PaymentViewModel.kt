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
    var payIsEnabled by mutableStateOf(false)

    fun initializeSession() {
        viewModelScope.launch {
            stripeUtil.setupPaymentSession()
        }
    }

    fun createPaymentIntent() {
        viewModelScope.launch {
            stripeUtil.createPaymentIntent(260.0)
            stripeUtil.selectedPayMethod()
        }
    }

    fun selectShipping() {
        stripeUtil.selectShipping()
    }

    fun confirmPaymentIntent() {
        stripeUtil.confirmPaymentIntent()
    }


}