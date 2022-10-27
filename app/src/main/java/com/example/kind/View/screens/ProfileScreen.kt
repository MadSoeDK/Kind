package com.example.kind.View.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.View.composables.Form
import com.example.kind.View.home.composables.HeaderAndText
import com.example.kind.ViewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    Column {
        HeaderAndText(headerProvider = "Header", textProvider = "text")
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button(onClick = {viewModel.onFormSubmit()} ) {
            Text("Submit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(viewModel = ProfileViewModel())
}