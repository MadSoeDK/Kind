package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val navController: NavController
) : ViewModel() {

    // Setup the homeState Dataclass
    data class HomeState(
        var amountDonated: Int = 0,
        var articles: List<com.example.kind.model.Article> = listOf(),
        var charities: List<com.example.kind.model.Charity> = listOf()
    )

    // State setup
    val storage: StorageServiceImpl = StorageServiceImpl()
    private val _data = MutableStateFlow(HomeState()) //storage.getCharity(id)
    val data: StateFlow<HomeState> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            _data.update {
                val charities = storage.getCharities()
                val articles = storage.getHomeArticles(charities.get(0).id)
                it.copy(
                    amountDonated = data.value.amountDonated,
                    articles = articles,
                    charities = charities//data.value.charities
                )
            }
        }
    }


}