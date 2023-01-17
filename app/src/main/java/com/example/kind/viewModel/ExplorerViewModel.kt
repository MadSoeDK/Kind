package com.example.kind.viewModel

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class CharityCategory(category: String) {
    All("All"),
    Health("Health"),
    Disasters("Disasters"),
    Climate("Climate"),
    Welfare("Welfare"),
    Children_Care("Children Care")
}
class ExplorerViewModel(
    val navController: NavController
) : ViewModel() {

    var storage: StorageServiceImpl = StorageServiceImpl()

    var charityList: List<com.example.kind.model.Charity> = mutableListOf()

    private val _data =
        MutableStateFlow(listOf<com.example.kind.model.Charity>()) //storage.getCharity(id)
    val data: StateFlow<List<com.example.kind.model.Charity>> = _data.asStateFlow()

    init {
        getCharities()
    }

    fun getCharities(): List<com.example.kind.model.Charity> {
        viewModelScope.launch {
            try {
                _data.update {
                    storage.getCharities()
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        return charityList
    }
    fun getCharitiesByCategory(category: String): List<com.example.kind.model.Charity> {
        viewModelScope.launch {
            try {
                _data.update {
                    storage.getCharitiesByCategory(category)
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        return charityList
    }
}
