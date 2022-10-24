package com.example.kind.View.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.View.home.composables.HomeScreen
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.ViewModel.PortfolioViewModel

fun PortfolioScreen(viewModel: PortfolioViewModel) {



    //TABLEs


}
@Composable
@Preview(showBackground = true)
fun PortfolioScreenPreview() {
    val viewModel = viewModel<PortfolioViewModel>()
    PortfolioScreen(
        viewModel
    )
}