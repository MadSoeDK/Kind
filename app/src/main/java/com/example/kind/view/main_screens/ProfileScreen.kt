package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.kind.view.composables.Form
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderAndText(headerProvider = "Account Settings", textProvider = "Edit your personal settings below")
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button(onClick = {viewModel.onFormSubmit()} ) {
            Text("Submit")
        }
        Button(
            onClick = { viewModel.deleteUser() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text("Delete User")
        }
        Button(onClick = onLogout) {
            Text(text = "Logout")
        }
    }
}