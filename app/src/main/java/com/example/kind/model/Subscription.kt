package com.example.kind.model

import com.google.firebase.Timestamp

data class Subscription(
    val amount: Double,
    val charityID: String,
    val ID: String,
    val initDate: Timestamp
        )