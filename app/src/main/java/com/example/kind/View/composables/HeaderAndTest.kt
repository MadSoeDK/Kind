package com.example.kind.View.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.View.theme.Typography

@Composable
fun HeaderAndText(
    donatedAmountProvider: String,
    welcomeText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.padding(0.dp, 10.dp)
        ){
            Text(
                text = donatedAmountProvider,
                fontWeight = Typography.h1.fontWeight,
                fontSize = Typography.h1.fontSize,
            )
        }
        Row {
            Text(text = welcomeText)
        }
    }
}