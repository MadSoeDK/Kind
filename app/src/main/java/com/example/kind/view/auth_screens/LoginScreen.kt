package com.example.kind.view.signup_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.viewModel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Form (
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button(
            modifier = Modifier
                .width(280.dp),
            onClick = { viewModel.login(viewModel.formState.getData()) },
        ) {
            Text("Login", color = MaterialTheme.colorScheme.onPrimary)
        }
        Text(text = "Forgot password", modifier = Modifier.clickable { /* TODO */ }  )
        Spacer(modifier = Modifier.height(40.dp))
    }
}