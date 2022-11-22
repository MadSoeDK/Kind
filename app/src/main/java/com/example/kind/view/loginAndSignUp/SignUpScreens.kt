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
    finishSignup: () -> Unit,
    back: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                Text(text = "About kind page")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
                }
            }
            1 -> SignUpPersonalInformationScreen(
                viewModel = SignUpPersonalInformationViewModel(),
                next = { viewModel.steps.value += 1 },
                back = back
            )
            2 -> {
                Text(text = "Build portfolio")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
                }
                Button(onClick = finishSignup) {
                    Text(text = "Make it later")
                }
            }
            3 -> SignUpDonationFrequencyScreen(
                viewModel = SignUpDonationFrequencyViewModel(),
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            4 -> SignUpDonationAmountScreen(
                viewModel = SignUpDonationAmountViewModel(),
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            5 -> Text(text = "Donation amount")
            6 -> Text(text = "Portfolio builder")
            7 -> {
                Text(text = "Donation summary")
                Button(onClick = finishSignup) {
                    Text(text = "Donate")
                }
            }
        }
        when (viewModel.steps.value) {
            in 5..6 -> {
                SignupNavigation(
                    nextStep = { viewModel.steps.value += 1 },
                    prevStep = { viewModel.steps.value -= 1 }
                )
            }

        }
    }
}

@Composable
fun SignupNavigation(
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