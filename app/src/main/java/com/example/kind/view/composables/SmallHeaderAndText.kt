package com.example.kind.view.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun SmallHeaderAndText(
    headerProvider: String,
    textProvider: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.padding(0.dp, 10.dp)
            ) {
                Text(
                    text = headerProvider,
                    fontWeight = Typography.headlineLarge.fontWeight,
                    fontSize = Typography.headlineMedium.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
        }
        Text(text = textProvider)
    }
}