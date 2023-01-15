package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.kind.paymentLauncher
import com.example.kind.viewModel.PaymentViewModel
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher

@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel
) {
    val paymentLauncher = paymentLauncher(viewModel = viewModel)

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
                paymentLauncher.confirm(ConfirmPaymentIntentParams.createWithPaymentMethodId(
                    paymentMethodId = viewModel.paymentId,
                    clientSecret = viewModel.clientSecret
                ))
            }
        ) {
            Text("Pay")
        }
    }
}