package com.example.kind.View.composables

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.kind.View.theme.Typography

@Composable
fun TextFieldColumn(title: String, fields: List<KindTextField>, state: FormState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Text(
                text = title,
                fontWeight = Typography.headlineMedium.fontWeight,
                fontSize = Typography.headlineLarge.fontSize,
                color = Typography.headlineLarge.color
            )
        }
        Form(state = state, fields = fields)
    }
}

