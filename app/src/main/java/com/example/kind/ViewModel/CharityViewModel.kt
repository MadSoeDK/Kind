package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.getFakeArticles

class CharityViewModel(
    val navController: NavController
): ViewModel() {
    fun getArticles(): List<Article> {
        return getFakeArticles()
    }
}