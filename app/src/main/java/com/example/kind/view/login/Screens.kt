package com.example.kind.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun StartScreen(
    navController: NavController
) {
    Column {
        Text(text = "Start screen")
        Button(onClick = { navController.navigate("signup") }) {
            Text(text = "Signup")
        }
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginScreen() {
    Text(text = "Login")
}

@Composable
fun SignupScreen() {
    Text(text = "Signup")
}