package com.example.kind.view.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.ViewModel.LoginViewModel
import com.example.kind.view.theme.KindTheme
import com.example.kind.view.theme.subHeading
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.background
import com.example.kind.view.theme.primary

/*class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView()
                }
            }
        }
    }
}*/

@Composable
fun LoginView(viewModel: LoginViewModel) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxWidth()) {


    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(subHeading)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ){
        LoginFields(
            username,
            password,
            onLoginClick = {viewModel.login(username, password)},
            onUsernameChange = {username = it},
            onPasswordChange = {password = it}
        )
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.background(background)
    ) {
        LoginHeader(96)
    }
}

@Composable
fun LoginFields(
  username: String,
  password: String,
  onUsernameChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onLoginClick: (String) -> Unit
){
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Log ind", color = background)

        OutlinedTextField(
            modifier = Modifier.background(background),
            value = username,
            placeholder = { Text("Brugernavn")},
            label = {Text("Brugernavn")},
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

        Button(
            onClick = {
            if (!username.isBlank() || !password.isBlank()) {
                onLoginClick(username)
                focusManager.clearFocus()
            } else{
                //TODO:indsæt "fejl: indsæt log ind oplysninger", har problemer med 'toast' og 'snackbar' implementering
            }
        }) {
            Text("Log ind", color = background )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text("Ny bruger? ")
            ClickableText(AnnotatedString("Opret konto"), onClick = { /*TODO*/})

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KindTheme {
        val viewModel = viewModel<LoginViewModel>()
        LoginView(viewModel)
    }
}