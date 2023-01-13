package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonOutlined
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
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(40.dp))
        KindButton(onClick = navigateToSignup, textProvider = "Signup")
        Spacer(modifier = Modifier.height(8.dp))
        KindButtonOutlined(onClick = {}, textProvider = "Continue with Google")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable { navigateToLogin() },
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}