package com.example.kind.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.*
import com.example.kind.view.theme.Typography
import com.example.kind.ViewModel.PortfolioViewModel
import com.example.kind.view.home.composables.HeaderAndText

val visible = mutableStateOf(false)

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    Column {
        if (visible.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                editPortfolio(viewModel = viewModel)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp)
            ) {
                HeaderAndText(
                    headerProvider = viewModel.getMonthlyDonatedAmount() + "kr.",
                    textProvider = "Du donerer hver måned 300 kr. til 2 temaer og 2 organisationer."
                )
            }
            PieChart(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally),
                progress = viewModel.getPercentages(),
                colors = viewModel.getColors()
            )

            // Labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                verticalArrangement = Arrangement.Top, Alignment.Start
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(85.dp), content = {
                    items(viewModel.getPortfolioDonation().size) { i ->
                        viewModel.getPortfolioDonation()[i].organization
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                Modifier
                                    .clip(RectangleShape)
                                    .background(viewModel.getColors()[i])
                                    .height(12.dp)
                                    .width(12.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }
                        if (!(viewModel.getPortfolioDonation()[i].organization.length > 20)) {
                            Text(
                                text = viewModel.getPortfolioDonation()[i].organization + "",
                                fontWeight = Typography.headlineMedium.fontWeight,
                                fontSize = Typography.labelSmall.fontSize,
                                color = Typography.headlineLarge.color,
                                textAlign = TextAlign.Center,
                            )
                        } else {
                            Text(
                                text = viewModel.getPortfolioDonation()[i].organization + "",
                                fontWeight = Typography.headlineMedium.fontWeight,
                                fontSize = Typography.labelSmall.fontSize.div(1.4),
                                color = Typography.headlineLarge.color,
                                textAlign = TextAlign.Center,
                            )
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

            PortfolioTable(
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
                data = viewModel.getPortfolioDonation(),
                headerCellContent = { index ->
                    val value = when (index) {
                        0 -> "Organization"
                        1 -> "%"
                        2 -> "Next month"
                        3 -> "Total"
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
                        0 -> item.organization
                        1 -> item.pct.toString() + "%"
                        2 -> item.spend.toString() + " kr."
                        3 -> item.total.toString() + " kr."
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editPortfolio(viewModel: PortfolioViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
            Box(
                Modifier
                    .clip(RectangleShape)
                    .background(Color.White)
                    .height(420.dp)
                    .width(320.dp)
            ) {
                Column(modifier = Modifier.padding(0.dp, 20.dp)) {
                    // Organisationer i et grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        Modifier.height(270.dp), horizontalArrangement = Arrangement.Start,
                        content = {
                            items(viewModel.getPortfolioDonation().size) { i ->
                                viewModel.getPortfolioDonation()[i].organization
                                Spacer(modifier = Modifier.padding(0.dp, 20.dp))

                                Text(
                                    text = viewModel.getPortfolioDonation()[i].organization,
                                    fontWeight = Typography.headlineMedium.fontWeight,
                                    fontSize = Typography.labelSmall.fontSize.times(1.5),
                                    color = Typography.headlineLarge.color,
                                    textAlign = TextAlign.Center,
                                )

                                var text by rememberSaveable { mutableStateOf("") }
                                Box(
                                    modifier = Modifier.width(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    TextField(
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier.padding(0.dp, 30.dp),
                                        value = text,
                                        onValueChange = {
                                            text = it
                                        },
                                        label = { Text("Amount (DKK)") },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color(
                                                0xFF1385623
                                            ).copy(alpha = 0.2F)
                                        ),
                                        textStyle = TextStyle.Default.copy(fontSize = 18.sp)
                                    )

                                }
                            }
                        })
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total: ",
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
                    Button(
                        onClick = { /*TODO*/ },
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
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.padding(0.dp, 10.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editButton() {

}

@Composable
@Preview(showBackground = true)
fun PortfolioScreenPreview() {
    val viewModel = viewModel<PortfolioViewModel>()
    PortfolioScreen(
        viewModel
    )
}