package com.example.kind.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.R
import com.example.kind.view.theme.Typography

@Composable
fun LoginHeader(size: Int, description: String? = null) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row {
            Image(
                painterResource(id = R.drawable.bekindsplashart1),
                contentDescription = "",
                Modifier.size((size * 1.75).dp)
            )
        }
        if (description != null) {
            Text(
                text = description,
                fontWeight = Typography.headlineSmall.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginHeaderPreview() {
    LoginHeader(96, "")
}