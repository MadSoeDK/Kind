package com.example.kind.model

data class User(
    val id : String,
    var name : String?,
    var email : String,
    var password : String,
    var age : Int?,
    var portfolio : List<Charity>?
)

