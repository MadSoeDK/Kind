package com.example.kind.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.view.composables.Form
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.viewModel.ProfileViewModel

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