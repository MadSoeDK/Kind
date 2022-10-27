package com.example.kind.View.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.View.home.composables.HeaderAndText
import com.example.kind.ViewModel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onButtonClick: () -> Unit
) {
    HeaderAndText(donatedAmountProvider = "Profile info", welcomeText = "", horizontalAlignment = Alignment.Start)
    TextField(value = "Helloworld", onValueChange = { handleChange("Change") })

}

fun handleChange(input: String) {

}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(viewModel = ProfileViewModel()) {

    }
}