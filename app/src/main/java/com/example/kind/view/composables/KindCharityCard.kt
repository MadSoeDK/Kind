package com.example.kind.view.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.kind.view.theme.Typography

@Composable
fun KindCharityCard(
    Title: String,
    Body: String,
    iconImage: String,
    ReadMore: String = "Read More",
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                AsyncImage(
                    model = iconImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(
                            CircleShape
                        )
                        .border(1.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.headlineSmall.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color,
                )
            }
            OutlinedButton(onClick = { onClick() }) {
                Text(
                    text = ReadMore,
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
        }
    }
}