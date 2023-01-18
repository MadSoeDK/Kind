package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.HomeScreens
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.composables.KindCard
import com.example.kind.view.theme.*
import com.example.kind.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state by viewModel.data.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHomeArticles()
    }

    Column {
        Spacer(modifier = Modifier.padding(0.dp, 15.dp))
        HeaderAndText(state.amountDonated.toString() + " kr.", "Your total donated amount")
        Spacer(modifier = Modifier.padding(0.dp, 10.dp))
        Column(modifier = Modifier.padding(20.dp, 0.dp)) {
            Text(
                text = "Charity News",
                color = MaterialTheme.colorScheme.primary,
                style = Typography.headlineMedium,
                fontSize = 24.sp
            )
            Text("The latest news from your charities", fontSize = 14.sp)
        }
        if (state.charities.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow {
                state.articles.forEachIndexed { i, element ->
                    item {
                        // Add left padding to first element
                        if (i == 0) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        KindCard(
                            titleProvider = element.charityName,
                            subTitleProvider = element.title,
                            iconImage = element.iconImage,
                            mainImage = element.mainImage,
                            onClick = { viewModel.navController.navigate( HomeScreens.Article.route + "/" + element.id) })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(0.dp, 10.dp))
        Column(modifier = Modifier.padding(20.dp, 0.dp)) {
            Text(
                text = "Explore charities",
                color = MaterialTheme.colorScheme.primary,
                style = Typography.headlineLarge,
                fontSize = 24.sp
            )
            Text("Get to know other charities better", fontSize = 14.sp)

        }

        if (state.charities.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow {
                state.charities.forEachIndexed { i, element ->
                    item {
                        if (i == 0) Spacer(modifier = Modifier.width(10.dp))
                        KindCard(
                            titleProvider = element.name,
                            iconImage = element.iconImage,
                            mainImage = element.mainImage,
                            subTitleProvider = element.name,
                            onClick = {
                                viewModel.navController.navigate(HomeScreens.Charity.route + "/" + element.id)
                            },
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}