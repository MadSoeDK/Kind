package com.example.kind.model

data class User(
    var name: String?,
    var monthlyPayment: Int = 0,
    var paymentMethod: String = "mobilepay"
)

