package com.example.kind.model

import java.util.Date

data class Donation(
    val amount: Double,
    val charityID: String,
    val charityName: String,
    val currency: String,
    val date: com.google.firebase.Timestamp,
    val description: String,
    val ID: String
)