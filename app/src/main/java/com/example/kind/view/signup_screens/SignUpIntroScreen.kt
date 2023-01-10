package com.example.kind.view.signup_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.R

@Composable
fun SignUpIntroScreen(
    navigateToHome : () -> Unit,
    navigateToPortfolio : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bekindsplashart1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Build your portfolio of charity now?",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "You can use the app for one-time donations and make you portfolio later. However you get the most benefits with a portfolio.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp)
        )
        Button(onClick = navigateToHome) {
            Text(text = "Build portfolio later")
        }
        TextButton(onClick = navigateToPortfolio) {
            Text(text = "Build now")
        }
    }
}