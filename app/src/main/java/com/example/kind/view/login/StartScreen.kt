package com.example.kind.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(
    signup: () -> Unit,
    login: () -> Unit
) {
    Column {
        Text(text = "Start screen")
        Button(onClick = signup) {
            Text(text = "Signup")
        }
        Button(onClick = login) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginScreen() {
    Text(text = "Login")
}