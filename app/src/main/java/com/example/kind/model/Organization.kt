package com.example.kind.model

data class Organization(
    val id : Int,
    var name : String,
    var charities : List<Charity>,
)
