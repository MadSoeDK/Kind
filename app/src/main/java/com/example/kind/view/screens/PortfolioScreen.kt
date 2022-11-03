package com.example.kind.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.PieChart
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.model.Portfolio
import com.example.kind.view.composables.PortfolioTable
import com.example.kind.view.theme.Typography
import com.example.kind.ViewModel.PortfolioViewModel

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    Column {
        HeaderAndText(headerProvider = viewModel.getMonthlyDonatedAmount() + "kr.", textProvider = "Du donerer hver måned 300 kr. til 2 temaer og 2 organisationer.")
        PieChart(modifier = Modifier.size(250.dp).align(Alignment.CenterHorizontally),
            progress = listOf(10f, 20f, 5f),
            colors = listOf(
                Color(0xFFbf95d4),
                Color(0xFFf4ac1a),
                Color(0xFF8b0a50)
            )
        )
        Text(text = "Your charities", fontSize = 24.sp, textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp), fontWeight = FontWeight.Black,
            color = Typography.headlineMedium.color)

        PortfolioTable (
            modifier = Modifier,
            columnCount = 4,
            cellWidth = { index ->
                when (index) {
                    0 -> 120.dp
                    1 -> 50.dp
                    2 -> 90.dp
                    3 -> 65.dp
                    else -> 70.dp
                }
            },
            data = listOf(
                Portfolio("Røde Kors", 121, 100, 150),
                Portfolio("UNICEF", 35, 100, 250),
                Portfolio("Mødrehjælpen", 26, 101, 540),
                Portfolio("Julehjælpen", 32, 100, 125),
            ),
            headerCellContent = { index ->
                val value = when (index) {
                    0 -> "Organization"
                    1 -> "%"
                    2 -> "Next month"
                    3 -> "Total"
                    else -> ""
                }
                val alignment = when(index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    fontSize = 16.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
            },
            cellContent = { index, item ->
                val value = when (index) {
                    0 -> item.organization
                    1 -> item.pct.toString() + "%"
                    2 -> item.spend.toString() + " kr."
                    3 -> item.total.toString() + " kr."
                    else -> ""
                }
                val alignment = when(index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    fontSize = 16.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PortfolioScreenPreview() {
    val viewModel = viewModel<PortfolioViewModel>()
    PortfolioScreen(
        viewModel
    )
}