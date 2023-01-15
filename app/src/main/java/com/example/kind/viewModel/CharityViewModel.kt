package com.example.kind.viewModel

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
    val id: String,
) : ViewModel() {

    // State setup
    val storage: StorageServiceImpl = StorageServiceImpl()
    private val _data = MutableStateFlow(Charity()) //storage.getCharity(id)
    val data: StateFlow<Charity> = _data.asStateFlow()

    init {
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
                    articles = storage.getArticles(charity.id)//charity.articles
                )
            }
        }
    }
}