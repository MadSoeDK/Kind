package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.background
import com.example.kind.view.theme.primary

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(background)
            .fillMaxSize()
    ){
        LoginHeader(96)
        Form (
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Button( //TODO: farvefix
            modifier = Modifier.width(280.dp),
            onClick = { viewModel.login(viewModel.formState.getData()) }
        ) {
            Text("Login", color = background )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text("Forgot Password? ", color = primary)
            ClickableText(AnnotatedString(text = "Click here"), onClick = { /*TODO*/}) //TODO: farvefix

        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text("New user? ", color = primary)
            ClickableText(AnnotatedString(text = "Signup here"), onClick = { viewModel.signUp()}) //TODO: farvefix
        }
    }
}