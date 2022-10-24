package com.example.kind.View.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.View.composables.PieChart
import com.example.kind.View.home.composables.HeaderAndText
import com.example.kind.Model.Portfolio
import com.example.kind.View.theme.Typography
import com.example.kind.ViewModel.PortfolioViewModel

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        HeaderAndText(headerProvider = viewModel.getMonthlyDonatedAmount() + "kr.", textProvider = "Du donerer hver måned 300 kr. til 2 temaer og 2 organisationer.")
        PieChart(modifier = Modifier,
            progress = listOf(10f, 20f, 5f),
            colors = listOf(
                Color(0xFFbf95d4),
                Color(0xFFf4ac1a),
                Color(0xFF8b0a50)
            )
        )
        Text(text = "Your charities", fontSize = 24.sp, textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp), fontWeight = FontWeight.Black,
            color = Typography.h1.color)

        tableValues()
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
@Composable
fun <T> Table (
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, Color.LightGray ),
                            contentColor = Color.Transparent,
                            modifier = Modifier.width(cellWidth(columnIndex))
                        ) {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun tableValues() {
    val portfolio = listOf(
        Portfolio("Røde Kors", 121, 100, 150),
        Portfolio("UNICEF", 35, 100, 250),
        Portfolio("Mødrehjælpen", 26, 101, 540),
        Portfolio("Julehjælpen", 32, 100, 125),
    )
    val cellWidth: (Int) -> Dp = { index ->
        when (index) {
            0 -> 130.dp
            1 -> 40.dp
            2 -> 90.dp
            3 -> 65.dp
            else -> 70.dp
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val value = when (index) {
            0 -> "Organization"
            1 -> "%"
            2 -> "Next month"
            3 -> "Total"
            else -> ""
        }

        Text(
            text = value,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Black,
            textDecoration = TextDecoration.Underline
        )
    }
    val cellText: @Composable (Int, Portfolio) -> Unit = { index, item ->
        val value = when (index) {
            0 -> item.organization
            1 -> item.pct.toString()
            2 -> item.spend.toString()
            3 -> item.total.toString()
            else -> ""
        }
        Text(
            text = value,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

    }

    Table(
        columnCount = 4,
        cellWidth = cellWidth,
        data = portfolio,
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}