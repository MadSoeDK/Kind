package com.example.kind.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.Charity
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharityViewModel(
    val navController: NavController,
    val storage: StorageServiceImpl,
    val id: String,
    val onAddToPortfolio: () -> Unit,
    val charities: State<PortState>
) : ViewModel() {
    // State setup
    private val _data = MutableStateFlow(Charity())
    val data: StateFlow<Charity> = _data.asStateFlow()

    init {
        update()
    }

    fun update() {
        viewModelScope.launch {
            _data.update {
                val charity = storage.getCharity(id)
                it.copy(
                    donaters = charity!!.donaters,
                    donations = charity.donations,
                    id = charity.id,
                    desc = charity.desc,
                    iconImage = charity.iconImage,
                    mainImage = charity.mainImage,
                    name = charity.name,
                    articles = storage.getArticles(charity.id),
                    inPortfolio = charity.inPortfolio
                )
            }
            charities.value.subscription.forEach {
                if (it.charityID == _data.value.id) {
                    _data.update {
                        it.copy(
                            inPortfolio = true
                        )
                    }
                }
            }
        }
    }

    fun addToPortfolio(){
        viewModelScope.launch {
            storage.addToPortfolio(data.value.id)
            onAddToPortfolio()
        }
        _data.update { it.copy(inPortfolio = true) }
    }

    fun removeFromPortfolio(){
        viewModelScope.launch {
            storage.removeFromPortfolio(data.value.id)
            onAddToPortfolio()
        }
        _data.update { it.copy(inPortfolio = false) }
    }
}