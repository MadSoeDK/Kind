package com.example.kind.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun SignupScreen(
    viewModel: SignupViewModel
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(Color.Gray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when(viewModel.steps.value) {
            0 -> {
                Text(text = "About")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
                }
            }
            1 -> Text(text = "Build portfolio")
            2 -> Text(text = "Build portfolio 2")
        }

        when(viewModel.steps.value) {
            in 1..4 -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { viewModel.steps.value -= 1 }) {
                        Text(text = "Back")
                    }
                    Button(onClick = { viewModel.steps.value += 1 }) {
                        Text(text = "Next")
                    }
                }
            }
        }

    }
}