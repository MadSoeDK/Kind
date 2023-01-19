package com.example.kind.view.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun CharityHeaderAndSubsectionText(
    Title: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp),
        verticalArrangement = Arrangement.Top, Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = Title,
            fontWeight = Typography.headlineLarge.fontWeight,
            fontSize = Typography.headlineSmall.fontSize,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}