package com.example.kind.view.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.AppViewModel
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        //Text(text = "Start building your portfolio of kindness today", style = Typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150, "Start building your portfolio of kindness today")
        }
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button(
            onClick = {
                viewModel.onFormSubmit(viewModel.formState.getData())
                viewModel.onSignUp(viewModel.formState.getData())
                }) {
            Text("Submit")
        }
        if (!viewModel.userIsCreated) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 15.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom
        ) {
            TextButton(onClick = { back() }) {
                Text("← Back")
            }
            //TODO Remove?
            Button(onClick = { next() }) {
                Text("Next →")
            }
        }
    }
}