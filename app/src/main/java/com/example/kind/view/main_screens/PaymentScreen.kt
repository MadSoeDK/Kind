package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.KindButton
import com.example.kind.viewModel.PaymentViewModel
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult

@Composable
fun paymentLauncher(
    viewModel: PaymentViewModel,
    publishedKey: String,
): PaymentLauncher {
    return PaymentLauncher.rememberLauncher(publishedKey) {
        when(it) {
            is PaymentResult.Completed -> {
                viewModel.paymentSuccess = true
            }
            else -> {
                // TODO Error handling
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel,
    charityName: String,
) {
    val paymentLauncher = paymentLauncher(viewModel = viewModel, viewModel.getPublishableKey())
    val paymentState by viewModel.paymentState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setupPaymentSession()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (viewModel.paymentPending) {
            if (!viewModel.paymentSuccess) {
                Text(text = "Request is pending. Please wait")
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator()
            } else {
                Row {
                    Text(text = "Payment succeeded")
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Test")
                }
                Text(text = "Thank you for your generosity")
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    viewModel.paymentPending = false
                    viewModel.paymentSuccess = false
                    viewModel.navigateOnPaymentSuccess
                }) {
                    Text(text = "Ok")
                }
            }
        } else {
            Text(text = "Specify amount to donate to $charityName")
            Spacer(modifier = Modifier.height(20.dp))
            TextField (
                value = paymentState.amount.toString(),
                onValueChange = { value ->
                    viewModel.textFieldError = false
                    if(value.isNotEmpty())
                        viewModel.updateStateAmount(amount = value.toInt())
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = viewModel.textFieldError,
                supportingText = { if(viewModel.textFieldError) Text(text = "Minimum 10 DKK donation") }
            )
            if (paymentState.payIsEnabled == true && paymentState.paymentMethod != null) {
                Spacer(modifier = Modifier.height(20.dp))
                val paymethod = paymentState.paymentMethod!!
                Text(text = "Selected payment method: " + paymethod.type?.name +  " ")
                Text(text = paymethod.card?.brand.toString() + " ****" + paymethod.card?.last4)
            } else {
                Spacer(modifier = Modifier.height(20.dp))
                KindButton(
                    onClick = {
                        if(paymentState.amount!! < 10) {
                            viewModel.textFieldError = true
                            return@KindButton
                        }
                        viewModel.createPaymentIntent(charityName)
                    },
                    textProvider = "Select Payment Method"
                )
            }
            

            if (paymentState.clientSecret != null && paymentState.payIsEnabled == true) {
                Spacer(modifier = Modifier.height(20.dp))
                KindButton(
                    onClick = {
                        viewModel.paymentPending = true
                        viewModel.paymentSuccess = false
                        if (paymentState.paymentMethod == null)
                            return@KindButton //Error

                        println("Payment confirm state $paymentState")
                        paymentLauncher.confirm(ConfirmPaymentIntentParams.createWithPaymentMethodId(
                            paymentMethodId = paymentState.paymentMethod?.id!!,
                            clientSecret = paymentState.clientSecret!!
                        ))
                    },
                    textProvider = "Pay",
                )
            }
        }
    }
}