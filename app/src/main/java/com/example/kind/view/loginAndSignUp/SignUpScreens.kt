package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kind.view.screens.PortfolioBuilderScreen

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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                Text(text = "About kind page")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
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
                buildMyOwn = { viewModel.steps.value += 1 },
                next = { viewModel.steps.value += 2 },
                back = { viewModel.steps.value -= 1 })
            6 -> SignUpDonationSelectionScreen(
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            7 -> SummaryScreen(
                next = { finishSignup() },
                back = { viewModel.steps.value -= 1 })
        }
    }
}