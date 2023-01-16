package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.HomeScreens
import com.example.kind.model.Article
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val navController: NavController,
    val storage: StorageServiceImpl
) : ViewModel() {

    // Setup the homeState Dataclass
    data class HomeState(
        var amountDonated: Int = 0,
        var articles: List<com.example.kind.model.Article> = listOf(),
        var charities: List<com.example.kind.model.Charity> = listOf()
    )

    // State setup
    private val _data = MutableStateFlow(HomeState()) //storage.getCharity(id)
    val data: StateFlow<HomeState> = _data.asStateFlow()

    init {
        if (storage.currentUser != null) {
            getHomeArticles()
        }
    }

    fun getHomeArticles() {
        viewModelScope.launch {
            _data.update {
                val charities = storage.getCharities()
                val articles = storage.getHomeArticles(charities[0].id)
                it.copy(
                    amountDonated = data.value.amountDonated,
                    articles = articles,
                    charities = charities//data.value.charities
                )
            }
        }
    }
}