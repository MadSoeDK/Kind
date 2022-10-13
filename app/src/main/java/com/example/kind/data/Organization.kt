package com.example.kind.data

data class Organization(
    val id : Int,
    var name : String,
    var charities : List<Charity>,
)
