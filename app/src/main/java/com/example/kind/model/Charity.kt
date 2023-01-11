package com.example.kind.model

data class Charity(

    var donaters: Int = 0,
    var donations: Int = 0,
    val id: String = "",
    val desc: String = "Check out this charity with the botton below",
    val iconImage: String = "",
    val mainImage: String = "",
    var name: String = "",
    val articles: List<Article> = listOf()

    /*
    val id : Int,
    var name : String,
    var organization : String,
    var supporters : List<User>,
    var theme : String,*/
)
