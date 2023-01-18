package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.Article
import com.example.kind.model.Charity
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
        var amountDonated: Int = 150,
        var articles: List<Article> = listOf(),
        var charities: List<Charity> = listOf()
    )

    // State setup
    private val _data = MutableStateFlow(HomeState())
    val data: StateFlow<HomeState> = _data.asStateFlow()

    fun getHomeArticles() {
        viewModelScope.launch {
            _data.update {
                val charities = storage.getCharities()
                val articles = storage.getHomeArticles(charities.get(0).id)
                val donationAmount = storage.getDonationsAmount()
                it.copy(
                    amountDonated = donationAmount,
                    articles = articles,
                    charities = charities
                )
            }
        }
    }
}