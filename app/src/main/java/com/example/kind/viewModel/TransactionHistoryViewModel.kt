package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kind.model.Donation
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionHistoryViewModel(
) : ViewModel() {
    private val storage: StorageServiceImpl = StorageServiceImpl()
    private val auth: AccountServiceImpl = AccountServiceImpl()
    private var data: List<Donation> = listOf()


    fun getDonations() : List<Donation>{
                viewModelScope.launch {
                    data = storage.getDonations(auth.userid)
        }
        return data
    }
}