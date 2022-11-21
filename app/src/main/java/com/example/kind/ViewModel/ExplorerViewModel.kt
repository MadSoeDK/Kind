package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.example.kind.Charity
import com.example.kind.getFakeCharities

class ExplorerViewModel(
    val navController: NavController
) : ViewModel() {

    fun getCharities(): List<Charity> {
        return getFakeCharities()
    }

}
