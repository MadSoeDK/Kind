package com.example.kind.view.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes
import com.example.kind.viewModel.DonationFrequency

//TODO set button to fixed size, ignoring the content
@Composable
fun DonationFreqScreen(
    selectedOption: DonationFrequency,
    onOptionSelected: (DonationFrequency) -> Unit
) {
    LoginHeader("How often will you make a donation")
    DonationFrequency.values().forEach {
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(
            onClick = { onOptionSelected(it) },
            shape = Shapes.extraSmall,
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isSystemInDarkTheme()) {
                    if (it == selectedOption) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.outline
                } else {
                    if (it == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
                },
                contentColor = if (isSystemInDarkTheme()) {
                    if (it == selectedOption) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onBackground
                } else {
                    if (it == selectedOption) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
                },
            )
        ) {
            Text(text = it.toString())
        }
    }
}