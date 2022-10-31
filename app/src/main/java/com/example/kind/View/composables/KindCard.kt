package com.example.kind.View.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.kind.R


@Composable
fun KindCard (
    titleProvider: String,
    subTitleProvier: String,
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .width(200.dp)
            .height(200.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_africankid_background),
                contentDescription = "African Children",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
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