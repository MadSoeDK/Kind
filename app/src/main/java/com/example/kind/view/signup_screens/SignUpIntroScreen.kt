package com.example.kind.view.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.LoginHeader

@Composable
fun SignUpIntroScreen(
    navigateToHome: () -> Unit,
    navigateToPortfolio: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LoginHeader(titleText = "You have now created a user. Do you want to build your portfolio of charity now?")
        Text(
            text = "You can use the app for one-time donations and make you portfolio later. However you get the most benefits with a portfolio.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp)
        )
        KindButton(onClick = navigateToPortfolio , textProvider = "Build now")
        TextButton(onClick = navigateToHome) {
            Text(text = "Build portfolio later")
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}