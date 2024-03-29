package com.example.kind.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author: https://alexzh.com/jetpack-compose-building-grids/
 *
 * Used in PortfolioScreen to display charity information
 */
@Composable
fun <T> PortfolioTable(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier,
    // TODO: Handle these two composables in PortfolioTable and instead only provide data
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyRow(
            modifier = Modifier.padding(30.dp)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    (0..data.size).forEach { index ->
                        Surface(
                            modifier = Modifier.width(cellWidth(columnIndex)),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color(0xFF79747E))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}