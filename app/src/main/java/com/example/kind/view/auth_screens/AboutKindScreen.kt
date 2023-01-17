package com.example.kind.view.signup_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.R

@Composable
fun AboutKindScreen(
    next: () -> Unit
) {
    Column {
        val textLogo = painterResource(id = R.drawable.textlogo)
        Column(
            modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = textLogo, contentDescription = null, modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            val painter = painterResource(id = R.drawable.bekindsplashart2)
            Image(
                painter = painter, contentDescription = null, modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            Text(text = "Who we are",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Kind is a platform available on web and mobile that makes giving personal, simple and effective by helping users build charitable portfolios they can support with just one monthly donation",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Our Mission",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = "To reinforce a culture of generosity by creating charitable giving solution that are fun and effective",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground)
            Button(onClick = { next() }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
@Preview (showBackground = true)
fun AboutKindScreenPreview(){
    AboutKindScreen {}
}