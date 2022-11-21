package com.example.kind

class Article(val id: Int, val header: String)
class Charity(val id: Int, val name: String)

fun getFakeArticles(): List<Article> {
    return listOf(Article(1, "Article 1"), Article(2, "Article 2"))
}

fun getFakeCharities(): List<Charity> {
    return listOf(Charity(1, "Charity 1"), Charity(2, "Charity 2"))
}