package com.example.kind.view.auth_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kind.AuthenticationScreens

@Composable
fun AuthenticationScreen(navController: NavController) {
    Column {
        Text("Hello world")
        Button(onClick = { navController.navigate(AuthenticationScreens.Login.route) }) {
            Text(text = "To login")
        }
    }
}