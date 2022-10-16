package com.example.kind.View.home.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.View.theme.Shapes

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())
    Card (
        shape = Shapes.medium,
        border = BorderStroke(1.dp, Color.Black)
    ){
        Column (
            modifier = Modifier.padding(10.dp),
        ){
            Row {
                Text(text = "Title")
            }
            Row {
                Text(text = "Subtitle")
            }
        }
    }
}