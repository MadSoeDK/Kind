package com.example.kind.model

import com.google.firebase.Timestamp

data class Subscription(
    var amount: Double = 0.0,
    val charityID: String = "",
    val id: String = "",
    val initDate: Timestamp? = null
)