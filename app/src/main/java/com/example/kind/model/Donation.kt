package com.example.kind.model

import com.google.firebase.Timestamp
import java.util.Date

data class Donation(
    val amount: Double? = 0.0,
    val charityID: String? = "",
    val charityName: String? = "",
    val currency: String? = "",
    val date: Timestamp? = Timestamp(Date()),
    val description: String? = "",
    val ID: String? = ""
)

data class Payment(
    val amount: Double? = 0.0,
    val charity_id: String? = "Red barnet",
    val currency: String? = "",
    val date: Timestamp? = Timestamp(Date()),
)