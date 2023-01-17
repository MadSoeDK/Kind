package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.LoginHeader
import com.example.kind.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToResetPassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LoginHeader("Your Portfolio of Kindness")
        if (!viewModel.isLoading) {
            Form (
                state = viewModel.formState,
                fields = viewModel.fields,
            )
            KindButton(
                onClick = { viewModel.onAuthentication(viewModel.formState.getData()) },
                textProvider = "Login"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Forgot password",
                modifier = Modifier.clickable {
                    navigateToResetPassword()
                },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
            )
        } else {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}