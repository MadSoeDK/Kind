package com.example.kind.View.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.View.composables.CardListHorizontalScroll
import com.example.kind.View.theme.Typography
import com.example.kind.View.theme.subHeading
import com.example.kind.ViewModel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())

        Text(text = "Charity Update", color = subHeading, fontSize = Typography.h2.fontSize, fontWeight = Typography.h2.fontWeight)
        Text("The latest news from your charities")

        CardListHorizontalScroll(viewModel.getArticles("Article 1", "Article 2"))
        
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Explore charities", color = subHeading, fontSize = Typography.h2.fontSize, fontWeight = Typography.h2.fontWeight)
        Text("Get to know other charities better")
        CardListHorizontalScroll(listOfContent = viewModel.getCharities("Charity 1", "Charity 2"))
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