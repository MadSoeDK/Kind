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
        //TODO this might need to be done in a different way since using the phone back button at any time takes the user back to the start of the signup flow

        when (viewModel.steps.value) {
            0 -> AboutKindScreen(
                next = { viewModel.steps.value += 1 }
            )
            1 -> PersonalInformationScreen(
                viewModel = SignupViewModel(),
                next = { viewModel.steps.value += 1 },
                back = back
            )
            2 -> {
                SignUpIntroScreen(
                )
                Button(onClick = { viewModel.steps.value += 1 }, modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)) {
                    Text(text = "Start")
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Button(onClick = { finishSignup() /*Might need to direct somewhere else*/}, modifier = Modifier
                    .width(200.dp)
                    .height(40.dp), colors = ButtonDefaults.buttonColors(background)) {
                    Text(text = "Make it later", color = Color.Black)
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
                buildMyOwn = { viewModel.steps.value += 1 },
                next = { viewModel.steps.value += 2 },
                back = { viewModel.steps.value -= 1 })
            6 -> SignUpDonationSelectionScreen(
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            7 -> SummaryScreen(
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            8 -> MobilePayScreen()
        }
        when (viewModel.steps.value) {
            8 -> {
                SignupNavigation(
                    nextStep = { finishSignup() },
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