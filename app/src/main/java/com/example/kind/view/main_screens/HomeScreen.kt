package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.*
import com.example.kind.viewModel.HomeViewModel
import com.example.kind.view.composables.KindCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())
    Column {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Charity Update",
                    color = MaterialTheme.colorScheme.primary,
                    style = Typography.headlineMedium
                )
                Text("The latest news from your charities")
            }
        }
    }
    LazyRow {
        viewModel.getArticles().forEachIndexed { i, element ->
            item {
                if (i == 0) {
                    Spacer(modifier = Modifier.width(10.dp))
                }
                /*if (i==0) {
                    KindCard(
                        modifier = Modifier.padding(),
                        titleProvider = element.header,
                        subTitleProvier = element.header,
                        onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                        }*/
                KindCard(
                    titleProvider = element.header,
                    subTitleProvider = element.header,
                    iconImage = " ", /*TODO*/
                    mainImage = " ", /*TODO*/
                    onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
            }
        }
    }


    Spacer(modifier = Modifier.height(50.dp))

    Column {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Explore charities",
                    color = MaterialTheme.colorScheme.primary,
                    style = Typography.headlineLarge
                )
                Text("Get to know other charities better")
            }
        }
    }

    LazyRow {
        viewModel.getCharities().forEachIndexed { i, element ->
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