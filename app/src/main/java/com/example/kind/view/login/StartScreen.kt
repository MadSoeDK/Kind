package com.example.kind.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(
    navigate: () -> Unit
) {
    Column {
        Text(text = "Start screen")
        Button(onClick = navigate) {
            Text(text = "Signup")
        }
        Button(onClick = navigate) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginScreen() {
    Text(text = "Login")
}