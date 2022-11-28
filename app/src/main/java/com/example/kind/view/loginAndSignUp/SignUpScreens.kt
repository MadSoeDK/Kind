package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.ViewModel.ExplorerViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.kind.view.screens.PortfolioBuilderScreen
import com.example.kind.view.theme.background

@OptIn(ExperimentalFoundationApi::class)
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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (viewModel.steps.value) {
            0 -> {
                SignUpIntroScreen(
                    viewModel = SignUpDonationSummaryViewModel())
                Button(onClick = { viewModel.steps.value += 1 }, modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)) {
                    Text(text = "Start")
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Button(onClick = { finishSignup() }, modifier = Modifier
                    .width(200.dp)
                    .height(40.dp), colors = ButtonDefaults.buttonColors(background)) {
                    Text(text = "Make it later", color = Color.Black)
                }
            }
            1 -> PersonalInformationScreen(
                viewModel = SignupViewModel(),
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
            3 -> {
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(DonationFrequency.Monthly) }
                DonationFreqScreen(
                    next = { viewModel.setFrequency(selectedOption) },
                    back = { viewModel.steps.value -= 1 },
                    selectedOption,
                    onOptionSelected
                )
            }
            4 -> {
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(50) }
                DonationAmountScreen(
                    next = { viewModel.setAmount(selectedOption) },
                    back = { viewModel.steps.value -= 1 },
                    selectedOption,
                    onOptionSelected,
                )
            }
            5 -> PortfolioBuilderScreen(
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            6 -> Text(text = "Portfolio builder")
            7 -> SummaryScreen(
                next = { finishSignup() },
                back = { viewModel.steps.value -= 1 })
        }
        when (viewModel.steps.value) {
            6 -> {
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
            Text(text = "← Back")
        }
        Button(onClick = nextStep) {
            Text(text = "Next →")
        }
    }
}