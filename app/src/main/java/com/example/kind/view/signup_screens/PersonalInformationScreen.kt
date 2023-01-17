package com.example.kind.view.signup_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader
import com.example.kind.viewModel.SignupViewModel

@Composable
fun PersonalInformationScreen(
    viewModel: SignupViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeader("Start building your portfolio of kindness today")
        if (!viewModel.userIsCreated) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator()
        } else {
            Form(
                state = viewModel.formState,
                fields = viewModel.fields,
            )
        }
    }
}