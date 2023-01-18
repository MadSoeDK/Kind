package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.kind.view.composables.PortfolioTable
import com.example.kind.viewModel.TransactionHistoryViewModel
import kotlin.math.roundToInt

@Composable
fun TransactionHistoryScreen(
    viewModel : TransactionHistoryViewModel,
    back: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getDonations()
    }

    val state by viewModel.data.collectAsState()

    Spacer(modifier = Modifier.height(10.dp))

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = back,
            modifier = Modifier.zIndex(1f),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "Transactions")
    }

    if(state.isEmpty()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            PortfolioTable(
                columnCount = 4,
                cellWidth = { index ->
                    when (index) {
                        0 -> 120.dp
                        1 -> 70.dp
                        2 -> 110.dp
                        else -> 70.dp
                    }
                },
                data = state,
                headerCellContent = { index ->
                    val value = when (index) {
                        0 -> "Organization"
                        1 -> "Date"
                        2 -> "Amount"
                        else -> ""
                    }
                    val alignment = when (index) {
                        0 -> TextAlign.Left
                        2 -> TextAlign.Right
                        else -> TextAlign.Center
                    }
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        textAlign = alignment,
                        modifier = Modifier.padding(0.dp, 20.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier,
                cellContent = { index, item ->
                    val value = when (index) {
                        0 -> item.charity_id
                        1 -> item.date?.toDate().toString()
                        2 -> "${item.amount?.roundToInt()} kr."
                        else -> ""
                    }
                    val alignment = when (index) {
                        0 -> TextAlign.Left
                        else -> TextAlign.Center
                    }
                    Text(
                        text = value!!,
                        fontSize = 13.sp,
                        textAlign = alignment,
                        modifier = Modifier.padding(0.dp, 20.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            )
        }
    }

    
}