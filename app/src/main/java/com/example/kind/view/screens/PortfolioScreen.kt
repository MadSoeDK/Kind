package com.example.kind.view.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.*
import com.example.kind.ViewModel.PortfolioViewModel
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.*

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    Column {
        if (viewModel.isOpen) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingSize.xxl),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                EditPortfolio(viewModel = viewModel)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        paddingSize.s,
                        paddingSize.s,
                    )
            ) {
                HeaderAndText(
                    headerProvider = viewModel.getMonthlyDonatedAmount() + "kr.",
                    textProvider = "Du donerer hver måned 300 kr. til 2 temaer og 2 organisationer."
                )
            }
            PieChart(
                modifier = Modifier
                    .size(size.xxl)
                    .align(Alignment.CenterHorizontally),
                progress = viewModel.getPercentages(),
                colors = viewModel.getColors(),
            )

            // Labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        paddingSize.xxxl,
                        paddingSize.default,
                    ),
                verticalArrangement = Arrangement.Top, Alignment.Start
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    Modifier.height(size.xs),
                    content = {
                    items(viewModel.getPortfolioDonation().size) { i ->
                        viewModel.getPortfolioDonation()[i].organization
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                Modifier
                                    .clip(RectangleShape)
                                    .background(viewModel.getColors()[i])
                                    .height(size.xxxxxs)
                                    .width(size.xxxxxs)
                                    .align(Alignment.BottomStart,
                                    )
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
                    .padding(
                        paddingSize.s,
                        paddingSize.s,
                    )
            ) {
                Text(
                    text = "Your charities",
                    fontSize = fontSize.xl,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(paddingSize.xxs),
                    fontWeight = FontWeight.Black,
                    color = Typography.headlineMedium.color,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        paddingSize.s,
                        paddingSize.s,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                PortfolioTable(
                    modifier = Modifier,
                    columnCount = 4,
                    cellWidth = { index -> //TODO
                        /*when (index) {
                            0 -> MaterialTheme.divDpSize.xl
                            1 -> MaterialTheme.divDpSize.xs
                            2 -> MaterialTheme.divDpSize.l
                            3 -> MaterialTheme.divDpSize.s
                            else -> MaterialTheme.divDpSize.m
                        }*/
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
                            fontSize = fontSize.m,
                            textAlign = alignment,
                            modifier = Modifier.padding(
                                paddingSize.default,
                                paddingSize.l,
                            ),
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
                            fontSize = fontSize.s,
                            textAlign = alignment,
                            modifier = Modifier.padding(
                                paddingSize.default,
                                paddingSize.l,
                            ),
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(modifier = Modifier.clip(MaterialTheme.shapes.large)) {
            Box(
                Modifier
                    .clip(RectangleShape)
                    .background(Color.White)
                    .height(size.xxxxxl)
                    .width(size.xxxxl)
            ) {
                Column(modifier = Modifier.padding(
                    paddingSize.default,
                    paddingSize.l,
                )) {
                    // Organisationer i et grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        Modifier.height(size.xxxl),
                        horizontalArrangement = Arrangement.Start,
                        content = {
                            items(viewModel.getPortfolioDonation().size) { i ->
                                viewModel.getPortfolioDonation()[i].organization
                                Spacer(modifier = Modifier.padding(
                                    paddingSize.default,
                                    paddingSize.l,
                                ))
                                Text(
                                    text = viewModel.getPortfolioDonation()[i].organization,
                                    fontWeight = Typography.headlineMedium.fontWeight,
                                    fontSize = Typography.labelSmall.fontSize.times(1.5),
                                    color = Typography.headlineLarge.color,
                                    textAlign = TextAlign.Center,
                                )

                                var text by rememberSaveable { mutableStateOf("") }
                                Box(
                                    modifier = Modifier.width(size.xxxs),
                                    contentAlignment = Alignment.Center
                                ) {
                                    TextField(
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier.padding(
                                            paddingSize.default,
                                            paddingSize.xxl,
                                        ),
                                        value = text,
                                        onValueChange = {
                                            text = it
                                        },
                                        label = { Text("Amount (DKK)") },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = colorScheme.primary.copy(alpha = 0.2F)
                                            //containerColor = colors.primary.copy(alpha = 0.2F)
                                        ),
                                        textStyle = TextStyle.Default.copy(
                                            fontSize = fontSize.l
                                        )
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
                    Spacer(modifier = Modifier.padding(
                        paddingSize.default,
                        paddingSize.s,
                    ))
                    Button(
                        onClick = { /*TODO*/ },
                        Modifier
                            .width(size.xl)
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
                    Spacer(modifier = Modifier.padding(
                        paddingSize.default,
                        paddingSize.s,
                    ))
                }
            }
        }
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