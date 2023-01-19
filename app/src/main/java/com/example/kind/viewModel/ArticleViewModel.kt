package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.Article
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Handling of Article data to show to a user from a specific charity.
 */

class ArticleViewModel(
    val navController: NavController,
    val storage: StorageServiceImpl,
    val id: String,
) : ViewModel() {

    // State setup
    private val _data = MutableStateFlow(Article())
    val data: StateFlow<Article> = _data.asStateFlow()

    init {
        getArticleById()
    }

    fun getArticleById() {
        viewModelScope.launch {
            _data.update {
                val articleData = storage.getArticle(id)
                it.copy(
                    charityName = articleData!!.charityName,
                    title = articleData.title,
                    paragraf = articleData.paragraf,
                    iconImage = articleData.iconImage,
                    mainImage = articleData.mainImage,
                    author = articleData.author,
                    date = articleData.date,
                )
            }
        }
    }
}