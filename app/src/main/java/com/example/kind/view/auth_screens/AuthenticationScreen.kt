package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader

@Composable
fun AuthenticationScreen(
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        LoginHeader(96)
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "Your Portfolio of Kindness",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(40.dp))
        Button (
            modifier = Modifier
                .width(280.dp),
            onClick = navigateToSignup,
        ) {
            Text(
                "Signup",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .width(280.dp),
            colors = ButtonDefaults
                .outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Text(text = "Continue with Google")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable { navigateToLogin() },
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}