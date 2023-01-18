package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.LoginHeader
import com.example.kind.viewModel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    loginViewModel: LoginViewModel,
    navigateToLoginScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LoginHeader("Reset your password")
        // Show email response
        if (loginViewModel.emailSentAttempted) {
            if (loginViewModel.emailSentSuccesfully == false) {
                Text(
                    "Incorrect Email",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                Text(
                    "Recovery Email Sent!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            isError = false,
            label = { Text(text = "Email") },
            supportingText = {  if(!loginViewModel.emailSentSuccesfully && loginViewModel.emailSentAttempted) Text(text = "Incorrect email")
                                else if (loginViewModel.emailSentAttempted) Text(text = "Email Sent!") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        KindButton(
            onClick = { loginViewModel.sendResetPasswordEmail(text) },
            textProvider = "Send Reset Email"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Go to Login",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable {
                navigateToLoginScreen()
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}