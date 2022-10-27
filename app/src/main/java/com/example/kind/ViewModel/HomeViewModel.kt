package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    // Logic etc...
    fun getText(): String {
        return "Du er blandt top 5% af donorer. Godt gået!"
    }

    fun getDonatedAmount(): String {
        return 1534.toString() + " kr."
    }

    fun getCharities(charity1: String, charity2: String): List<String> {
        val articles = ArrayList<String>()
        articles.add(charity1)
        articles.add(charity2)
        return articles
    }

    fun getArticles(article1: String, article2: String): List<String> {
        val articles = ArrayList<String>()
        articles.add(article1)
        articles.add(article2)
        return articles
    }
}

