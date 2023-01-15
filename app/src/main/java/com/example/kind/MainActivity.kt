package com.example.kind

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.theme.KindTheme
import com.stripe.android.PaymentConfiguration
import com.stripe.android.view.PaymentMethodsActivityStarter

class MainActivity : ComponentActivity() {
    val storage = StorageServiceImpl()
    val stripeUtil = StripeUtil(this, storage)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PaymentConfiguration.init(
            applicationContext,
            BuildConfig.API_KEY
        )
        setContent {
            KindTheme {
                KindApp(stripeUtil, storage)
            }
        }
    }
    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.activity.ComponentActivity"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("Request-code:$requestCode Result-code $resultCode")
        stripeUtil.paymentSession?.handlePaymentData(requestCode, resultCode, data)
    }
}
