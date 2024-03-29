package com.example.kind.view.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.kind.view.theme.Typography

/**
 * Used to display charities only in the ExplorerScreen
 */
@Composable
fun KindExploreCard(
    Title: String,
    Body: String,
    iconImage: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .width(250.dp)
            .height(175.dp)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            ),
    ) {

        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = iconImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                    ,
                    contentScale = ContentScale.FillBounds,
                    )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = Title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = Typography.headlineSmall.fontSize.times(0.65),
                    color = Typography.headlineLarge.color
                )
            }
            Row(modifier = Modifier.padding(5.dp, 5.dp)) {
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize.times(0.85),
                    color = Typography.displayMedium.color,
                )
            }
        }
    }
}