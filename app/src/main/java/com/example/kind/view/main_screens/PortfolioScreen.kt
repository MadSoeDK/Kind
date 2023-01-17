package com.example.kind.view.main_screens

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
import androidx.compose.ui.graphics.RectangleShape
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
    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.data.collectAsState()
        if (viewModel.haveSubscriptions) {
            if (state.subscription.isEmpty()) {
                CircularProgressIndicator()
            } else {
                if (viewModel.popupIsOpen) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),

                        ) {
                        EditPortfolio(viewModel = viewModel)
                    }
                } else {
                    PortfolioContent(viewModel)
                }
            }
        } else {
            Column(
                modifier = Modifier.height(700.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "You have no charities in your portfolio")
                    Text(text = "Start building your portfolio today!")
                    Spacer(modifier = Modifier.height(20.dp))
                    KindButton(onClick = viewModel.onNavigateToCharities, textProvider = "Explore Charities and Build Portfolio")
                }
        }
    }
}

@Composable
fun PortfolioContent(viewModel: PortfolioViewModel) {
    val state by viewModel.data.collectAsState()
    HeaderAndText(
        headerProvider = "${state.subscription.sumOf { it.amount }.roundToInt()} kr.",
        textProvider = "You donate ${
            state.subscription.sumOf { it.amount }.roundToInt()
        } kr. to ${state.subscription.size} organizations."
    )

    PieChart(
        modifier = Modifier
            .size(250.dp),
        progress = state.subscription.map { it -> return@map (it.amount / state.subscription.sumOf { it.amount }).toFloat() },
        colors = state.color
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier
            .height(85.dp)
            .padding(horizontal = 30.dp),
        content = {
            state.subscription.forEachIndexed { i, it ->
                item {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .clip(RectangleShape)
                                .background(
                                    state.color[i]
                                )
                                .height(12.dp)
                                .width(12.dp)
                                .align(Alignment.BottomStart)
                        )
                        if (it.charityName.length <= 20) {
                            Text(
                                text = it.charityName + "",
                                fontWeight = Typography.headlineMedium.fontWeight,
                                fontSize = Typography.labelSmall.fontSize,
                                color = Typography.headlineLarge.color,
                                textAlign = TextAlign.Center,
                            )
                        } else {
                            Text(
                                text = it.charityName + "",
                                fontWeight = Typography.headlineMedium.fontWeight,
                                fontSize = Typography.labelSmall.fontSize.div(1.4),
                                color = Typography.headlineLarge.color,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        })

    Text(
        text = "Your charities", fontSize = 24.sp, textAlign = TextAlign.Center,
        modifier = Modifier.padding(6.dp), fontWeight = FontWeight.Black,
        color = Typography.headlineMedium.color
    )
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
                fontSize = 13.sp,
                textAlign = alignment,
                modifier = Modifier.padding(0.dp, 20.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPortfolio(viewModel: PortfolioViewModel) {
    val state by viewModel.data.collectAsState()
    var sum by remember { mutableStateOf(state.subscription.sumOf { it.amount }) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text("Edit your subscription amount for each charity.", textAlign = TextAlign.Center)
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            Modifier
                .padding(0.dp, 20.dp)
                .height(220.dp), horizontalArrangement = Arrangement.Start,
            content = {
                state.subscription.forEach { subscription ->
                    item {
                        var text by remember { mutableStateOf(subscription.amount.toString()) }
                        Box(
                            modifier = Modifier.padding(0.dp, 10.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            TextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = text,
                                onValueChange = { value: String ->
                                    text = value
                                    if (text.isNotEmpty()) {
                                        viewModel.updateSubscriptionState(
                                            subscription,
                                            value.toDouble()
                                        )
                                        sum = state.subscription.sumOf { amount -> amount.amount }
                                    }
                                },
                                singleLine = true,
                                label = { Text(subscription.charityID) },
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
                fontSize = Typography.displayMedium.fontSize,
                color = Typography.labelLarge.color,
            )
            Text(
                text = "The changes will take effect next month.",
                fontWeight = Typography.displayMedium.fontWeight,
                fontSize = Typography.labelLarge.fontSize,
                color = Typography.labelLarge.color,
            )
            Spacer(modifier = Modifier.padding(0.dp, 10.dp))
            val openDialog = remember { mutableStateOf(false) }
            KindButton(
                onClick = {
                    viewModel.updateSubscription()
                    openDialog.value = true
                },
                textProvider = "Save Changes"
            )

            if (openDialog.value == true) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                        viewModel.popupIsOpen = false
                    },
                    title = { Text(text = "Portfolio Update") },
                    text = {
                        Text(
                            text = "Your changes have been saved and your portfolio is now updated!",
                            fontWeight = FontWeight.Normal
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            openDialog.value = false
                            viewModel.popupIsOpen = false
                        }) {
                            Text("Ok")
                        }
                    },
                    shape = MaterialTheme.shapes.medium
                )
            }
            Spacer(modifier = Modifier.padding(0.dp, 10.dp))
        }
    }
}