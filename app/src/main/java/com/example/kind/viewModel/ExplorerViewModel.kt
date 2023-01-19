package com.example.kind.viewModel

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.Charity
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Handling of showing charities to the Explorer page with their data:
 *      -desc
 *      -iconImage
 *      -mainImage
 *      -name
 *      -articles
 *      -category
 *
 * Also connects charities by their category, so a user can filtrate their charities.
 */
class ExplorerViewModel(
    val navController: NavController,
    var storage: StorageServiceImpl
) : ViewModel() {
    private val _data = MutableStateFlow(listOf<Charity>())
    val data: StateFlow<List<Charity>> = _data.asStateFlow()

    fun getCharities() {
        viewModelScope.launch {
            try {
                _data.update {
                    storage.getCharities()
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    fun getCharitiesByCategory(category: String) {
        if(category == "All") {
            getCharities()
        } else {
            viewModelScope.launch {
                try {
                    _data.update {
                        storage.getCharitiesByCategory(category)
                    }
                } catch (e: Exception) {
                    println(e.printStackTrace())
                }
            }
        }
    }
}
