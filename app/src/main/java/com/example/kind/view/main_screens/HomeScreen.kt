package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.theme.*
import com.example.kind.viewModel.HomeViewModel
import com.example.kind.view.composables.KindCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val state by viewModel.data.collectAsState()

    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())
        Text(
            text = "Charity Update",
            color = MaterialTheme.colorScheme.primary,
            style = Typography.headlineMedium
        )
        Text("The latest news from your charities")
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
                            titleProvider = element.title,
                            subTitleProvider = element.charityName,
                            iconImage = element.iconImage,
                            mainImage = element.mainImage,
                            onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Explore charities",
            color = MaterialTheme.colorScheme.primary,
            style = Typography.headlineLarge
        )
        Text("Get to know other charities better")


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
                        if (i == 0) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
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
    }
}