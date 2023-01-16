package com.example.kind.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun HeaderAndText(
    headerProvider: String,
    textProvider: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(0.dp, 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = headerProvider,
                    fontWeight = Typography.headlineLarge.fontWeight,
                    fontSize = Typography.headlineLarge.fontSize,
                    color = Typography.headlineLarge.color,
                    textAlign = TextAlign.Center
                )
                Text(text = textProvider, textAlign = TextAlign.Center)
            }
        }
    }
}