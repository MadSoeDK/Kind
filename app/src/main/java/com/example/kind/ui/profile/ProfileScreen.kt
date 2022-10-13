package com.example.kind.ui.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onButtonClick: () -> Unit
) {
    Button(onClick = onButtonClick) {
        Text(text = viewModel.getButtonText())
    }
}