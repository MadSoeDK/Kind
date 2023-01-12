package com.example.kind.viewModel

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kind.Charity
import com.example.kind.Global
import com.example.kind.getFakeCharities
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        println("Attempting to get list of charities")
        viewModelScope.launch {
            try {
                _data.update {
                    storage.getCharities()
                }
                println("Succesfully fethced charity data!")

            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        println("Returning this data: " + charityList.toString())

        return charityList
    }
}
