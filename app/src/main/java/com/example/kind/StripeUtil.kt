package com.example.kind

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.kind.model.service.FirebaseEphemeralKeyProvider
import com.example.kind.model.service.impl.StorageServiceImpl
import com.stripe.android.*
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.PaymentMethod
import com.stripe.android.view.BillingAddressFields
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// Data class to hold Payment method data
data class PaymentMethodData(val id: String? = null, val paymentMethod: PaymentMethod? = null)

@Singleton
class StripeUtil @Inject constructor(
    @ApplicationContext val context: ComponentActivity,
    val storage: StorageServiceImpl
) {
    private val stripe: Stripe by lazy { Stripe(context, PaymentConfiguration.getInstance(context).publishableKey) }

    private val _paymentMethodData = MutableStateFlow(PaymentMethodData())
    val paymentMethodData: StateFlow<PaymentMethodData> = _paymentMethodData

    var clientSecret by mutableStateOf("")

    var paymentSession: PaymentSession? = null

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
                        Log.d("PaymentSession", "Ready to charge");
                        //payButtonIsEnabled = true

                        data.paymentMethod?.let { paymethod ->
                            Log.d("PaymentSession", "PaymentMethod $paymethod selected")
                            _paymentMethodData.update { it.copy(id = paymethod.id, paymentMethod = paymethod) }
                        }
                    }
                }

                override fun onCommunicatingStateChanged(isCommunicating: Boolean) {
                    Log.d("PaymentSession",  "isCommunicating $isCommunicating")
                }

                override fun onError(errorCode: Int, errorMessage: String) {
                    Log.e("PaymentSession",  "onError: $errorCode, $errorMessage")
                }
            }
        )
    }

    fun selectedPayMethod() {
        paymentSession?.presentPaymentMethodSelection()
    }

    fun createPaymentIntent(amount: Double, currency: String = "dkk") {
        CoroutineScope(Dispatchers.Default).launch {
            clientSecret = storage.createStripePaymentIntent(amount, currency)

        }
        stripe.confirmPayment(context, ConfirmPaymentIntentParams.createWithPaymentMethodId(_paymentMethodData.value.id.toString(), clientSecret))
    }

}