package com.example.kind.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.paddingSize
import com.example.kind.view.theme.size


@Composable
fun KindCard (
    titleProvider: String,
    subTitleProvier: String,
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddingSize.xs)
            .width(MaterialTheme.size.l)
            .height(MaterialTheme.size.l)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(MaterialTheme.size.m)
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
                        .height(MaterialTheme.size.xxxs)
                        .width(MaterialTheme.size.xxxs)
                )
                CircleShape
                Column (modifier = Modifier.padding(
                    MaterialTheme.paddingSize.xs,
                    MaterialTheme.paddingSize.default,
                )){
                    Text(text = titleProvider)
                    Text(text = subTitleProvier)
                }
            }
        }
    }
}