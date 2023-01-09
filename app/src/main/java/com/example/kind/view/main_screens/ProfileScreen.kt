package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.kind.view.composables.Form
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.viewModel.AuthViewModel
import com.example.kind.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    auth : AuthViewModel
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
            onClick = {viewModel.deleteUser()},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )) {
            Text("Delete User")

        }
        Button(onClick = { auth.onLogout() }) {
            Text(text = "Logout")
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun ProfileScreenPreview() {
//    ProfileScreen(viewModel = ProfileViewModel())
//}