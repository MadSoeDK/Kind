package com.example.kind

class Article(val id: Int, val header: String)
class Charity(val id: Int, val name: String)

private val articles = listOf(Article(0, "Article 1"), Article(1, "Article 2"))

private val charities = listOf(Charity(0, "Charity 1"), Charity(1, "Charity 2"))

fun getFakeArticles(): List<Article> {
    return articles
}

fun getFakeCharities(): List<Charity> {
    return charities
}

fun getFakeArticle(id: Int): Article {
    return articles[id]
}

fun getFakeCharity(id: String): Charity {
    return Charity(0, "Charity 1")//charities[id]
}