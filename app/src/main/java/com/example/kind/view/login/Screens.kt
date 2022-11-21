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
    viewModel: SignupViewModel,
    finishSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Gray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                Text(text = "About")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Next")
                }
            }
            1 -> Text(text = "Personal information")
            2 -> {
                Text(text = "Build portfolio")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
                }
                Button(onClick = { /*TODO skip rest*/ }) {
                    Text(text = "Make it later")
                }
            }
            3 -> Text(text = "Donation frequency")
            4 -> Text(text = "Donation amount")
            5 -> Text(text = "Portfolio builder")
            6 -> {
                Text(text = "Donation summary")
                Button(onClick = finishSignup) {
                    Text(text = "Donate")
                }
            }
        }
        when (viewModel.steps.value) {
            1 -> {
                SignupNavigation(
                    nextStep = { viewModel.steps.value += 1 },
                    prevStep = { viewModel.steps.value -= 1 }
                )
            }
            in 3..5 -> {
                SignupNavigation(
                    nextStep = { viewModel.steps.value += 1 },
                    prevStep = { viewModel.steps.value -= 1 }
                )
            }

        }
    }
}

@Composable
fun SignupNavigation (
    nextStep: () -> Unit,
    prevStep: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = prevStep) {
            Text(text = "Back")
        }
        Button(onClick = nextStep) {
            Text(text = "Next")
        }
    }
}