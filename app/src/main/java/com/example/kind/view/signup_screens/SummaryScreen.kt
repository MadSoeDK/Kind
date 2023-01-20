package com.example.kind.view.signup_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.view.composables.PortfolioTable
import com.example.kind.viewModel.SignupViewModel
import kotlin.math.roundToInt

@Composable
fun SummaryScreen(
    viewModel: SignupViewModel,
) {
    val state by viewModel.portfolioState.collectAsState()

    Column(modifier = Modifier.padding(15.dp, 25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Great! You are now set to donate " + viewModel.amountState + " kr. per month to the following charities",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = "Adjust the percentages to each organization below.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground,
        )

        PortfolioTable(
            modifier = Modifier,
            columnCount = 3,
            cellWidth = { index ->
                when (index) {
                    0 -> 120.dp
                    1 -> 70.dp
                    2 -> 110.dp
                    else -> 70.dp
                }
            },
            data = state.subscription,
            headerCellContent = { index ->
                val value = when (index) {
                    0 -> "Organization"
                    1 -> "%"
                    2 -> "DKK"
                    else -> ""
                }
                val alignment = when (index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
            },
            cellContent = { index, item ->
                println("Item :$item")
                val value = when (index) {
                    0 -> item.charityName
                    1 -> "${
                        (((item.amount + 0.00001) / state.subscription.sumOf { it.amount + 0.00001 }) * 100).roundToInt()
                    }%"
                    2 -> "${item.amount.roundToInt()} kr."
                    else -> ""
                }
                val alignment = when (index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    fontSize = 12.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        )
        Text(
            text = "Pr. month donation: " + viewModel.amountState.toString() + " kr",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        val checkedState = remember { mutableStateOf(true) }
        Row {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
            Text(
                text = "Accept terms of service.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 14.dp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}