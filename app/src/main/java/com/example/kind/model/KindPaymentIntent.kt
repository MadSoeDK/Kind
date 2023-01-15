package com.example.kind.model

data class KindPaymentIntent(
    val client_secret: String? = null,
    val customer: String? = null,
    val amount: Int? = null
)