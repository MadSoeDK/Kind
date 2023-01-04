package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ){
            LoginHeader(96)
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
        }
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.fillMaxHeight().height(180.dp)) //TODO: THIS IS BAD PRACTICE, BUT I GIVE UP!
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(280.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Text(text = "Forgot Password")
            }
            OutlinedButton(
                onClick = {viewModel.signUp()},
                modifier = Modifier
                    .width(280.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Text(text = "Signup")
            }
        }
    }
}