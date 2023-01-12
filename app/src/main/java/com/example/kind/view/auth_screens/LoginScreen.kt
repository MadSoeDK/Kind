package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader
import com.example.kind.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LoginHeader(96)
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "Your Portfolio of Kindness",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(40.dp))
        if (!viewModel.isLoading) {
            Form(
                state = viewModel.formState,
                fields = viewModel.fields,
            )
            Button(
                modifier = Modifier
                    .width(280.dp),
                onClick = { viewModel.onAuthentication(viewModel.formState.getData()) },
            ) {
                Text("Login", color = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Forgot password",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { /* TODO */ }
            )
        } else {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(40.dp))

    }

}