package com.example.kind.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.kind.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.PortfolioTemplateCard
import com.example.kind.view.theme.Typography

@ExperimentalFoundationApi
@Composable
fun PortfolioBuilderScreen(
    buildMyOwn: () -> Unit,
    next: () -> Unit,
    back: () -> Unit
) {
    Column {
        Row(modifier = Modifier
            .padding(20.dp, 20.dp)
            //.align(Alignment.CenterHorizontally)
            )
        {
            Text(
                text = "Get started with some of our templates or build your own",
                fontWeight = Typography.labelLarge.fontWeight,
                fontSize = Typography.headlineMedium.fontSize,
                color = Typography.headlineLarge.color)
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(500.dp),content = {
            items(10 /*TODO: Needs to be adaptive based on the templates*/) {
                PortfolioTemplateCard(
                Title = "Red Cross",
                Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                OrganizationIcon = painterResource(id = R.drawable.bekindsplashart1),
            )}
        })

        Row(modifier = Modifier
            .padding(0.dp, 10.dp)
            .align(Alignment.CenterHorizontally))
        {
            OutlinedButton(onClick = { buildMyOwn() }, modifier = Modifier.width(300.dp)) {
                Text(
                    text = "Build my own",
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = Typography.headlineLarge.color
                )
            }

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Column{
                TextButton(onClick = { back() }) {
                    Text(
                        text = "← Back",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineLarge.color
                    )
                }
            }
            Column{
                Button(onClick = { next() }) {
                    Text(
                        text = "Next →",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineSmall.color
                    )
                }
            }
        }
    }
}