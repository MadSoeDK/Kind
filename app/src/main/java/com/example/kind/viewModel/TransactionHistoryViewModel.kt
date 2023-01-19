package com.example.kind.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kind.model.Charity
import com.example.kind.model.Donation
import com.example.kind.model.Payment
import com.example.kind.model.service.impl.AccountServiceImpl
import com.example.kind.model.service.impl.StorageServiceImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 *
 */

class TransactionHistoryViewModel(
    private val storage: StorageServiceImpl,
) : ViewModel() {
    private val _data = MutableStateFlow<List<Payment>>(listOf())
    val data: StateFlow<List<Payment>> = _data.asStateFlow()

    fun getDonations() {
        viewModelScope.launch {
            _data.update { storage.getPayments(Firebase.auth.uid!!) }
        }
    }
}