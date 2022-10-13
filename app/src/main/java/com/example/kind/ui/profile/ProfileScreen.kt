package com.example.kind.ui.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit
) {
    Button(onClick = onNavigateToHome) {
        Text(text = "Profile screen")
    }
}