package com.example.kind.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.ViewModel.LoginViewModel

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