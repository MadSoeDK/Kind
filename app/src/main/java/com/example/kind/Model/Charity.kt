package com.example.kind.Model

data class Charity(
    val id : Int,
    var name : String,
    var organization : String,
    var supporters : List<User>,
    var theme : String,
)
