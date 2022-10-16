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

    fun getArticles(): List<String> {
        val articles = ArrayList<String>()
        articles.add("Article 1")
        articles.add("Article 2")
        return articles
    }
}

