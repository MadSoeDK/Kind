package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.KindTheme
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (String) -> Unit,
    signUp: () -> Unit
){
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Login", color = primary)

        OutlinedTextField(
            modifier = Modifier.background(background),
            value = username,
            placeholder = { Text("Username")},
            label = {Text("Username")},
            onValueChange = onUsernameChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()})
        )

        OutlinedTextField(
            modifier = Modifier.background(background),
            value = password,
            placeholder = { Text("Password")},
            label = {Text("Password")},
            onValueChange = onPasswordChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()})
        )

        Button( //TODO: farvefix
            modifier = Modifier.width(280.dp),
            onClick = {
                if (!username.isBlank() || !password.isBlank()) {
                    onLoginClick(username)
                    focusManager.clearFocus()
                } else{
                    //TODO:indsæt "fejl: indsæt log ind oplysninger", har problemer med 'toast' og 'snackbar' implementering
                }
            }) {
            Text("Login", color = background )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text("Forgot Password? ", color = primary)
            ClickableText(AnnotatedString(text = "Click here"), onClick = { /*TODO*/}) //TODO: farvefix

        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text("New user? ", color = primary)
            ClickableText(AnnotatedString(text = "Signup here"), onClick = { signUp()}) //TODO: farvefix
        }
    }
}