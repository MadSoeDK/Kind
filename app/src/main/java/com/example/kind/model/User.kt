package com.example.kind.model

data class User(
    var name: String?,
    var monthlyPayment: Double? = null,
    var paymentMethod: String = "mobilepay"
)

