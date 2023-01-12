package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonDanger
import com.example.kind.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit
) {
    var read by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderAndText(
            headerProvider = "Account Settings",
            textProvider = "Edit your personal settings below"
        )
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        if(read) {
            Button(onClick = {
                read = !read
                viewModel.updateChanges()
            }) {
                Text("Update Changes")
            }
        }
        if (!read) {
            Button(onClick = {
                read = !read
                viewModel.saveChanges()
                viewModel.updateChanges()
            }) {
                Text("Save")
            }
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
        KindButton(onClick = { viewModel.onFormSubmit() }, textProvider = "Submit")
        KindButtonDanger(onClick = { viewModel.deleteUser() }, TextProvider = "Delete User")
        KindButton(onClick = onLogout, textProvider = "Logout")
    }
}