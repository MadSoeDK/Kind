package com.example.kind.viewModel

import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.example.kind.Charity
import com.example.kind.Global
import com.example.kind.getFakeCharities
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorerViewModel(
    val navController: NavController
) : ViewModel() {

    var storage: StorageServiceImpl = StorageServiceImpl()

    var charityList: List<com.example.kind.model.Charity> = mutableListOf()

    fun getCharities(): List<com.example.kind.model.Charity> {

        println("Attempting to get list of charities")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                charityList = storage.getCharities()

            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        return charityList
    }
}
