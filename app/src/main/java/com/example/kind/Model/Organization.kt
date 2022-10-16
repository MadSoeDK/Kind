package com.example.kind.Model

data class Organization(
    val id : Int,
    var name : String,
    var charities : List<Charity>,
)
