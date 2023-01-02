package com.example.kind.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.Screen
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.viewModel.HomeViewModel
import com.example.kind.view.composables.KindCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())

        Text(text = "Charity Update", color = subHeading, fontSize = Typography.headlineMedium.fontSize, fontWeight = Typography.headlineMedium.fontWeight)
        Text("The latest news from your charities")

        LazyRow {
            viewModel.getArticles().forEach {
                item {
                    KindCard(titleProvider = it.header, subTitleProvier = it.header, onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Explore charities", color = subHeading, fontSize = Typography.headlineMedium.fontSize, fontWeight = Typography.headlineMedium.fontWeight)
        Text("Get to know other charities better")

        LazyRow {
            viewModel.getCharities().forEach {
                item {
                    KindCard(titleProvider = it.name, subTitleProvier = it.name, onClick = { viewModel.navController.navigate(Screen.Charity.route + "/" + it.id.toString()) })
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val viewModel = viewModel<HomeViewModel>()
    HomeScreen(
        viewModel
    )
}