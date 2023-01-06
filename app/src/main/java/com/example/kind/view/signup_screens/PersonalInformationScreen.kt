package com.example.kind.view.signup_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150, "Start building your portfolio of kindness today")
        }
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button(onClick = {viewModel.createUser()} ) {
            Text("Submit")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 15.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom) {
            TextButton(onClick = {back()} ) {
                Text("← Back")
            }
            Button(onClick = {next()} ) {
                Text("Next →")
            }
        }
    }
}