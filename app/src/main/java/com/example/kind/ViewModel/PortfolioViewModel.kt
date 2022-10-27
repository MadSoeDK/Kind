package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel

class PortfolioViewModel : ViewModel() {

    fun getMonthlyDonatedAmount(): String {
        val amount: Int = 300
        return amount.toString()
    }

}