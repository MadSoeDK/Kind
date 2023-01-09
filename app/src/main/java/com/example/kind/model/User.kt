package com.example.kind.model

data class User(
    var id: String,
    var name: String?,
    var email: String?,
    var password: String?,
    var age: Int? = null,
    var monthlyPayment: Double? = null,
    var paymentMethod: String = "mobilepay"
)

