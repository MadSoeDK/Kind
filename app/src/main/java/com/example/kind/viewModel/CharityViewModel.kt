package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharity

class CharityViewModel(
    val navController: NavController,
    id: Int,
): ViewModel() {
    val data = getFakeCharity(id)

    fun getArticles(): List<Article> {
        return getFakeArticles()
    }
}