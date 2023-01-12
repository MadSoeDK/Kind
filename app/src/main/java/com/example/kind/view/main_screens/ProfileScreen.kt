package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
        KindButton(Onclick = { viewModel.onFormSubmit() }, TextProvider = "Submit")
        /*Button(onClick = { viewModel.onFormSubmit() }) {
            Text("Submit")
        }*/
        KindButtonDanger(Onclick = { viewModel.deleteUser() }, TextProvider = "Delete User")
        /*Button(
            onClick = { viewModel.deleteUser() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text("Delete User")
        }*/
        KindButton(Onclick = { onLogout }, TextProvider = "Logout")
        /*Button(onClick = onLogout) {
            Text(text = "Logout")
        }*/
    }
}