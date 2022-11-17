package com.example.kind.view.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize

@Composable
fun HeaderAndText(
    headerProvider: String,
    textProvider: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.xxxxl,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.s,
            )
        ){
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