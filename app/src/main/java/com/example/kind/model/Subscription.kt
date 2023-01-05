package com.example.kind.model

data class Subscription (
    val amount : Double,
    val charityID : String,
    val ID : String,
    val initDate : com.google.firebase.Timestamp
        )