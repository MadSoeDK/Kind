package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.Charity
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharity

class CharityViewModel(
    val navController: NavController
): ViewModel() {
    fun getArticles(): List<Article> {
        return getFakeArticles()
    }

    fun getCharity(): Charity {
        return getFakeCharity(1)
    }
}