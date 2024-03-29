package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kind.model.Subscription
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

/**
 * Retrieves data from the Storage Service about a user's subscriptions via getSubscriptions()
 * and updates a subscriptions amount based on user entered data from the Edit Portfolio Composable
 */

data class PortState(
    var subscription: List<Subscription> = emptyList(),
    var color: List<Color> = emptyList(),
)

class PortfolioViewModel(
    val storage: StorageServiceImpl,
    val onNavigateToCharities: () -> Unit
) : ViewModel() {

    private val _data = MutableStateFlow(PortState())
    val data: StateFlow<PortState> = _data.asStateFlow()

    var haveSubscriptions by mutableStateOf(false)

    var popupIsOpen by mutableStateOf(false)

    fun getSubscriptions() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subscriptions = storage.getSubscriptions()
                haveSubscriptions = subscriptions.isNotEmpty()
                _data.update {
                    _data.value.copy(
                        subscription = subscriptions,
                        color = getColors().subList(0, subscriptions.size),
                    )
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    fun toggleModal() {
        popupIsOpen = !popupIsOpen
    }

    fun updateSubscription() {
        CoroutineScope(Dispatchers.IO).launch {
            _data.value.subscription.forEach {
                storage.modifySubscriptionAmount(it, it.amount)
            }
        }
    }

    fun updateSubscriptionState(subscription: Subscription, amount: Double) {
        _data.value.subscription[_data.value.subscription.indexOf(subscription)].amount = amount
        _data.update { _data.value }
    }

    fun getColors(): List<Color> {
        val colors: MutableList<Color> = mutableListOf()
        colors.add(Color(0xFFbf95d4))
        colors.add(Color(0xFFf4ac1a))
        colors.add(Color(0xFF8b0a50))
        colors.add(Color(0xFFeb4034))

        colors.add(Color(0xFF27b0e6))
        colors.add(Color(0xFFa6e007))

        colors.add(Color(0xFFFF001D))
        colors.add(Color(0xFF07E082))
        colors.add(Color(0xFFA90AFF))
        colors.add(Color(0xFFFFE200))

        return colors
    }
}