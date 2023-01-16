package com.example.kind.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun HeaderAndText(
    headerProvider: String,
    textProvider: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Text(
                text = headerProvider,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineLarge.fontSize,
                color = Typography.headlineLarge.color
            )
        }
        Row {
            Text(text = textProvider)
        }
    }
}