package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.Charity
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharities

class HomeViewModel(
    val navController: NavController
) : ViewModel() {

    // Logic etc...
    fun getText(): String {
        return "Du er blandt top 5% af donorer. Godt gået!"
    }

    fun getDonatedAmount(): String {
        return 1534.toString() + " kr."
    }

    fun getCharities(): List<Charity> {
        return getFakeCharities()
    }

    fun getArticles(): List<Article> {
        return getFakeArticles()
    }
}

