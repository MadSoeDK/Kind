package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharity

class ArticleViewModel(
    val navController: NavController,
    id: Int,
): ViewModel() {
    val id = id
    val title = "Dummy Title"
    val paragraf = "Dummy paragraf "
    val charityName = "Dummy charity name"
}