package com.example.kind.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.kind.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    login: () -> Unit
) {
    Column {
        Text(text = "Login")
        Button(onClick = { login() }) {
            Text(text = "Login")
        }
    }
}