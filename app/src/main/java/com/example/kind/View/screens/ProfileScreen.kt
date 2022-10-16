package com.example.kind.View.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kind.ViewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onButtonClick: () -> Unit
) {
    Button(onClick = onButtonClick) {
        Text(text = viewModel.getButtonText())
    }
}