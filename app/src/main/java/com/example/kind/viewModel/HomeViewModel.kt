package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kind.Article
import com.example.kind.Charity
import com.example.kind.getFakeArticles
import com.example.kind.getFakeCharities
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.runBlocking

class HomeViewModel(
    val navController: NavController
) : ViewModel() {

    var storage: StorageServiceImpl = StorageServiceImpl()
    var charityList: List<com.example.kind.model.Charity> = mutableListOf()

    fun getCharities(): List<com.example.kind.model.Charity> {

        println("Attempting to get list of charities")
        runBlocking {
            try {
                charityList = storage.getCharities()
                println("Succesfully fethced charity data!")

            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        println("Returning this data: " + charityList.toString())

        return charityList
    }
    // Logic etc...
    fun getText(): String {
        return "Du er blandt top 5% af donorer. Godt gået!"
    }

    fun getDonatedAmount(): String {
        return 1534.toString() + " kr."
    }

    fun getArticles(): List<Article> {
        return getFakeArticles()
    }
}