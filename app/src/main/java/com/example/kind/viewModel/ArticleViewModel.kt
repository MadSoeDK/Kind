package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharity
import com.example.kind.model.Charity
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(
    val navController: NavController,
    id: String,
): ViewModel() {

    // State setup
    val storage: StorageServiceImpl = StorageServiceImpl()
    private val _data = MutableStateFlow(com.example.kind.model.Article()) //storage.getCharity(id)
    val data: StateFlow<com.example.kind.model.Article> = _data.asStateFlow()

    init{
        GlobalScope.launch {
            _data.update {
                val articleData = storage.getArticle(id)
                it.copy(
                    charityName = articleData!!.charityName,
                    title = articleData.title,
                    paragraf = articleData.paragraf
                )
            }
        }
    }

    val id = id
    val title = "Dummy Title"
    val paragraf = "Dummy paragraf "
    val charityName = "Dummy charity name"
}