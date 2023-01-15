package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.kind.viewModel.PaymentViewModel
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult

@Composable
fun paymentLauncher(
    viewModel: PaymentViewModel,
    publishedKey: String
): PaymentLauncher {
    return PaymentLauncher.rememberLauncher(publishedKey) {
        when(it) {
            is PaymentResult.Completed -> {
                // Do stuff
                println("Something is happening")
            }
            else -> {
                // Other stuff
            }
        }
    }
}

@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel
) {
    val paymentLauncher = paymentLauncher(viewModel = viewModel, viewModel.stripeUtil.getPublishasbleKey())
    val paymentMethodData by viewModel.stripeUtil.paymentMethodData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeSession()
    }

    Column {
        Button(
            onClick = {
                viewModel.createPaymentIntent()
            }
        ) {
            Text("Checkout")
        }

        Button(
            onClick = {
                //Confirm payment intent
                println(paymentMethodData.toString())
                println(viewModel.stripeUtil.clientSecret)
                paymentLauncher.confirm(ConfirmPaymentIntentParams.createWithPaymentMethodId(
                    paymentMethodId = paymentMethodData.id!!,
                    clientSecret = viewModel.stripeUtil.clientSecret
                ))
            }
        ) {
            Text("Pay")
        }
    }
}