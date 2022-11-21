package com.example.kind.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.kind.view.theme.size
import com.example.kind.view.theme.background
import com.example.kind.view.theme.paddingSize

@Composable
fun <T> PortfolioTable (
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier,
    // TODO: Handle these two composables in PortfolioTable and instead only provide data
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier.background(background), color = background
    ) {
        LazyRow(
            modifier = Modifier.padding(MaterialTheme.paddingSize.m)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            //border = BorderStroke(MaterialTheme.size.xxxxxs, Color.LightGray ),
                            //contentColor = Color.Transparent,
                            modifier = Modifier.width(cellWidth(columnIndex)), color = background
                        ) {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(MaterialTheme.size.xxxxxxs)
                                        .background(Color(0xFF79747E))) //TODO
                            }
                        }
                    }
                }
            }
        }
    }
}