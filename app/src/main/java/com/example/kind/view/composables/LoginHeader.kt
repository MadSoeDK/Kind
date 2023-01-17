package com.example.kind.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.R

@Composable
fun LoginHeader(titleText: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(0.dp, 30.dp)
    ) {
        Image(
            painterResource(id = R.drawable.bekindsplashart1),
            contentDescription = "Kind Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}