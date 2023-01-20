package com.example.kind.viewModel

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kind.model.PaymentState
import com.example.kind.model.service.FirebaseEphemeralKeyProvider
import com.example.kind.model.service.impl.StorageServiceImpl
import com.stripe.android.*
import com.stripe.android.model.PaymentMethod
import com.stripe.android.view.BillingAddressFields
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PaymentViewModel(
    @ApplicationContext val context: ComponentActivity,
    val storage: StorageServiceImpl,
    val navigateOnPaymentSuccess: () -> Unit = {}
) : ViewModel() {
    private val _paymentState = MutableStateFlow(PaymentState())
    val paymentState: StateFlow<PaymentState> = _paymentState

    var textFieldError by mutableStateOf(false)
    var paymentPending by mutableStateOf(false)
    var paymentSuccess by mutableStateOf(false)

    var paymentSession: PaymentSession? = null

    fun updateStateAmount (amount: Int) {
        _paymentState.update {
            it.copy(amount = amount)
        }
    }

    fun getPublishableKey(): String {
        return PaymentConfiguration.getInstance(context).publishableKey
    }

    fun setupPaymentSession() {
        CustomerSession.initCustomerSession(context = context, ephemeralKeyProvider = FirebaseEphemeralKeyProvider())

        paymentSession = PaymentSession(context, PaymentSessionConfig.Builder()
            .setShippingInfoRequired(false)
            .setShippingMethodsRequired(false)
            .setBillingAddressFields(BillingAddressFields.None)
            .build()
        )

        paymentSession?.init(
            object: PaymentSession.PaymentSessionListener {
                override fun onPaymentSessionDataChanged(data: PaymentSessionData) {
                    Log.d("PaymentSession", "PaymentSession has changed: $data")
                    Log.d("PaymentSession", "${data.isPaymentReadyToCharge} <> ${data.paymentMethod}")

                    if (data.isPaymentReadyToCharge) {
                        Log.d("PaymentSession", "Ready to charge")

                        data.paymentMethod?.let { paymethod ->
                            Log.d("PaymentSession", "PaymentMethod $paymethod selected")
                            _paymentState.update { it.copy(paymentMethod = paymethod, payIsEnabled = true) }
                        }
                    }
                }

                override fun onCommunicatingStateChanged(isCommunicating: Boolean) {
                    Log.d("PaymentSession",  "isCommunicating $isCommunicating")
                    _paymentState.update { it.copy(payIsEnabled = false) }
                }

                override fun onError(errorCode: Int, errorMessage: String) {
                    Log.e("PaymentSession",  "onError: $errorCode, $errorMessage")
                    _paymentState.update { it.copy(payIsEnabled = false) }
                }
            }
        )
    }

    fun createPaymentIntent(charityName: String) {
        viewModelScope.launch {
            try {
                paymentPending = true
                val amount = _paymentState.value.amount?.toDouble()!!
                val task = storage.createStripePaymentIntent(amount*100, "dkk", charityName)
                val result = task.await()
                withContext(Dispatchers.IO) {
                    // TODO: We do not at the moment have a better solution than this
                    // We have to wait for the firebase function to finish. This is very vulnerable to errors.
                    Thread.sleep(5000)
                    _paymentState.update { it.copy(clientSecret = storage.getClientSecret(result), payIsEnabled = true) }
                    paymentPending = false
                    paymentSession?.presentPaymentMethodSelection()
                }
            } catch (e: Exception) {
                paymentPending = false
                e.printStackTrace()
            }

        }
    }
}