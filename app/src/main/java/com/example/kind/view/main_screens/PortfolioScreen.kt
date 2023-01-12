package com.example.kind.view.main_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.view.composables.*
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.PortfolioViewModel
import kotlin.math.roundToInt

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    val state by viewModel.data.collectAsState()
    Column {
        if (viewModel.popupControl) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                EditPortfolio(viewModel = viewModel)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp)
            ) {
                HeaderAndText(
                    headerProvider = "${state.subscription.sumOf { it.amount }} kr.",
                    textProvider = "You currently donate ${state.subscription.sumOf { it.amount }} kr. to ${state.subscription.size} organizations."
                )
            }

            PieChart(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally),
                progress = state.subscription.map { it -> return@map (it.amount / state.subscription.sumOf { it.amount }).toFloat() },
                colors = state.color
            )

            // Labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                verticalArrangement = Arrangement.Top, Alignment.Start
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(85.dp), content = {
                    var j = 0
                    state.subscription.forEach {
                        item {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    Modifier
                                        .clip(RectangleShape)
                                        .background(
                                            state.color.get(j)
                                        )
                                        .height(12.dp)
                                        .width(12.dp)
                                        .align(Alignment.BottomStart)
                                )
                            }
                            if (!(it.charityID.length > 20)) {
                                Text(
                                    text = it.charityID + "",
                                    fontWeight = Typography.headlineMedium.fontWeight,
                                    fontSize = Typography.labelSmall.fontSize,
                                    color = Typography.headlineLarge.color,
                                    textAlign = TextAlign.Center,
                                )
                            } else {
                                Text(
                                    text = it.charityID + "",
                                    fontWeight = Typography.headlineMedium.fontWeight,
                                    fontSize = Typography.labelSmall.fontSize.div(1.4),
                                    color = Typography.headlineLarge.color,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            j++
                        }
                    }
                })
            }

            //Your charities text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp)
            ) {
                Text(
                    text = "Your charities", fontSize = 24.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp), fontWeight = FontWeight.Black,
                    color = Typography.headlineMedium.color
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PortfolioTable(
                    modifier = Modifier,
                    columnCount = 4,
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
                            2 -> "Next month"
                            else -> ""
                        }
                        val alignment = when (index) {
                            0 -> TextAlign.Left
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
                    cellContent = { index, item ->
                        val value = when (index) {
                            0 -> item.charityID
                            1 -> "${
                                ((item.amount / state.subscription.sumOf { it.amount })*100).roundToInt()
                            }%"
                            2 -> "${item.amount} kr."
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPortfolio(viewModel: PortfolioViewModel) {
    val state by viewModel.data.collectAsState()
    var sum by remember { mutableStateOf(state.subscription.sumOf { it.amount }) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 20.dp)) {
            // Organisationer i et grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                Modifier.height(330.dp), horizontalArrangement = Arrangement.Start,
                content = {
                    state.subscription.forEach { subscription ->
                        item {
                            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
                            Text(
                                text = subscription.charityID,
                                fontWeight = Typography.headlineMedium.fontWeight,
                                fontSize = Typography.labelSmall.fontSize.times(1.5),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )

                            var text by remember { mutableStateOf(subscription.amount.toString()) }
                            Box(
                                modifier = Modifier.width(40.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                TextField(
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.padding(0.dp, 30.dp),
                                    value = text,
                                    onValueChange = {
                                        text = it
                                        if (text.isNotEmpty()) {
                                            viewModel.updateSubscriptionState(
                                                subscription,
                                                it.toDouble()
                                            )
                                            sum = state.subscription.sumOf { it.amount }
                                        }
                                    },
                                    label = { Text("Amount (DKK)") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.White,

                                        ),
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    singleLine = true
                                )
                            }
                        }
                    }
                })
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total: " + sum.toString(),
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = Typography.labelLarge.color,
                )
                Text(
                    text = "The changes will take effect next month.",
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = Typography.labelLarge.color,
                )
                Spacer(modifier = Modifier.padding(0.dp, 10.dp))
                val openDialog = remember { mutableStateOf(false) }
                KindButton(onClick = {
                    viewModel.updateSubscription()
                    openDialog.value = true
                                     },
                    textProvider = "Save")
                /*Button(
                    onClick = {
                        viewModel.updateSubscription()
                        openDialog.value = true
                    },
                    Modifier
                        .width(200.dp)
                        .background(
                            Typography.headlineLarge.color
                        )
                ) {
                    Text(
                        text = "Save",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = MaterialTheme.colorScheme.background,
                    )
                }*/
                if (openDialog.value == true) {
                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                            viewModel.popupControl = false
                        },
                        title = { Text(text = "Portfolio Update") },
                        text = { Text(text = "Your changes have been saved and your portfolio is now updated!") },
                        confirmButton = {
                            Button(onClick = {
                                openDialog.value = false
                                viewModel.popupControl = false
                            }) {
                                Text("Ok")
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(0.dp, 10.dp))
            }
        }
    }
}