package com.example.kind.View.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun KindCard (
    titleProvider: String,
    subTitleProvier: String,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(200.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Text(text = "Image")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize(),
            ) {
                Box (
                    Modifier
                        .clip(CircleShape)
                        .background(Color.Red)
                        .height(40.dp)
                        .width(40.dp)
                )
                CircleShape
                Column (modifier = Modifier.padding(8.dp, 0.dp)){
                    Text(text = titleProvider)
                    Text(text = subTitleProvier)
                }
            }
        }
    }
}