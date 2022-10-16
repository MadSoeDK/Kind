package com.example.kind.Model

data class User(
    val id : Int?,
    var name : String,
    var email : String,
    var password : String,
    var age : Int,
    var portfolio : List<Charity>
)

