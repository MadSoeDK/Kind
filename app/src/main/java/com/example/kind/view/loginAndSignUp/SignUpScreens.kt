package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*@Composable
fun StartScreen(
    navController: NavController
) {
    //er ikke sikker på om vi skal have denne route, siden at start altid er ved login. beholder den for nu in case vi vil have en first time login besked eller lign.
}

@Composable
fun LoginScreen(navController: NavController) {
    LoginView()
}*/

@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    finishSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                SignUpPersonalInformationScreen(viewModel = SignUpPersonalInformationViewModel(), next = { viewModel.steps.value += 1 }) {
                    
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