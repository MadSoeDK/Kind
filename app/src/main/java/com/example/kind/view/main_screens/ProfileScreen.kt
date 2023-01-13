package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.Column
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
        KindButton(onClick = { viewModel.onFormSubmit() }, textProvider = "Submit")
        KindButtonDanger(onClick = { viewModel.deleteUser() }, TextProvider = "Delete User")
        KindButton(onClick = onLogout, textProvider = "Logout")
    }
}