package com.example.kind.ViewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.Screen
import com.example.kind.model.Organization
import com.example.kind.view.screens.OrganizationScreen

class ExplorerViewModel : ViewModel() {

    val organizations = listOf(Organization(0), Organization(1), Organization(2))

    @Composable
    fun getOrganization(id: Int){
        val viewModel = viewModel<HomeViewModel>()
        when(id){
            0 -> return OrganizationScreen(
                viewModel = viewModel,
                donorAmount = "4.8K",
                donationAmount = "103K",
                organizationName = "Red Barnet",
                organizationTheme = "Børnevelfærd"
            )
            1 -> return OrganizationScreen(
                viewModel = viewModel,
                donorAmount = "4.8K",
                donationAmount = "103K",
                organizationName = "Psykiatrifonden",
                organizationTheme = "Mentalt Helbred"
            )
            2 -> return OrganizationScreen(
                viewModel = viewModel,
                donorAmount = "4.8K",
                donationAmount = "103K",
                organizationName = "Cystisk Fibrose Foreningen",
                organizationTheme = "Mentalt Helbred"
            )

            // Get the first index
            else -> getOrganization(id = 0)
        }
    }
}
