package com.example.kind.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun KindCard(
    titleProvider: String,
    subTitleProvider: String = "",
    iconImage: String,
    mainImage: String,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clickable { onClick() }
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = MaterialTheme.shapes.medium
        )
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column (
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                AsyncImage(
                    model = mainImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp),
            ) {
                AsyncImage(
                    model = iconImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                        .background(MaterialTheme.colorScheme.background),
                    contentScale = ContentScale.FillBounds
                )
                Column(modifier = Modifier.padding(8.dp, 0.dp).background(MaterialTheme.colorScheme.background)) {
                    Text(
                        text = titleProvider,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = subTitleProvider,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}