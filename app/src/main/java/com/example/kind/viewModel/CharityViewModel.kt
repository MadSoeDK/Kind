package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharity
import com.example.kind.model.Charity
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharityViewModel(
    val navController: NavController,
    id: String,
): ViewModel() {

    val storage: StorageServiceImpl = StorageServiceImpl()
    var data = Charity()//storage.getCharity(id)

    init
    {
        GlobalScope.launch {
            data = storage.getCharity(id)
        }
    }

    fun getArticles(): List<Article> {
        return getFakeArticles()
    }
}