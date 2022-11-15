package com.example.kind.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.kind.R

@Composable
fun StartScreen(
    navController: NavController
) {
    Column {
        Text(text = "beKind")
        Image(
            painterResource(
                id = R.drawable.screenshot20220914071147),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
        )
        Text(text = "Start building your portfolio of kindness today!")
        Button(onClick = { navController.navigate("signup") }) {
            Text(text = "Signup")
        }
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginScreen() {
    Text(text = "Login")
}

@Composable
fun SignupScreen() {
    Text(text = "Signup")
}