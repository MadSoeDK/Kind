package com.example.kind.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize

@Composable
fun TextFieldColumn(title: String, fields: List<KindTextField>, state: FormState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                paddingSize.l,
                paddingSize.default,),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.s)
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

