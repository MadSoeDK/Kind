package com.example.kind.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.CardListHorizontalScroll
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.view.theme.size

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())

        Text(
            text = "Charity Update",
            color = subHeading,
            fontSize = Typography.headlineMedium.fontSize,
            fontWeight = Typography.headlineMedium.fontWeight,
        )
        Text("The latest news from your charities")

        CardListHorizontalScroll(viewModel.getArticles(
            "Article 1",
            "Article 2",
        ))
        
        Spacer(modifier = Modifier.height(MaterialTheme.size.xxs))

        Text(
            text = "Explore charities",
            color = subHeading,
            fontSize = Typography.headlineMedium.fontSize,
            fontWeight = Typography.headlineMedium.fontWeight,
        )
        Text("Get to know other charities better")
        CardListHorizontalScroll(listOfContent = viewModel.getCharities(
            "Charity 1",
            "Charity 2"
        ))
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