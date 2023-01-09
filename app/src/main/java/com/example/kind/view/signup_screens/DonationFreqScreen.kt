package com.example.kind.view.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes
import com.example.kind.viewModel.DonationFrequency

//TODO set button to fixed size, ignoring the content
@Composable
fun DonationFreqScreen(
    next: () -> Unit,
    back: () -> Unit,
    selectedOption: DonationFrequency,
    onOptionSelected: (DonationFrequency) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        ) {
        LoginHeader(size = 150, "How often will you make a donation")

        DonationFrequency.values().forEach {
            OutlinedButton (
                onClick = { onOptionSelected(it) },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .width(300.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (it == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = if(it == selectedOption) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer //onBackground skal måske skiftes
                )
            ) {
                Text(text = it.toString())
            }
        }
        Button(onClick = next) {
            Text(text = "Continue")
        }
        Button(onClick = back) {
            Text(text = "Back")
        }
    }
}