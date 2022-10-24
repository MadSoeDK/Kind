package com.example.kind.View.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.View.composables.PieChart
import com.example.kind.View.home.composables.HeaderAndText
import com.example.kind.ViewModel.PortfolioViewModel

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    Column {
        HeaderAndText(headerProvider = viewModel.getMonthlyDonatedAmount() + "kr.", textProvider = "Du donerer hver måned 300 kr. til 2 temaer og 2 organisationer.")
        PieChart(modifier = Modifier,
            progress = listOf(10f, 20f, 5f),
            colors = listOf(
                Color(0xFFbf95d4),
                Color(0xFFf4ac1a),
                Color(0xFF8b0a50)
            )
        )
    }
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