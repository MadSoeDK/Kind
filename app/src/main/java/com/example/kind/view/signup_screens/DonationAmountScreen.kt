package com.example.kind.view.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes

//TODO set button to fixed size, ignoring the content
@Composable
fun DonationAmountScreen(
    next: () -> Unit,
    back: () -> Unit,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        ) {
        LoginHeader(size = 150, "How much would you like to donate",)
        listOf(50, 100, 200).forEach {
            OutlinedButton (
                onClick = { onOptionSelected(it) },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .width(300.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSystemInDarkTheme()) {
                        if (it == selectedOption) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primary
                    } else {
                        if (it == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
                    },
                    contentColor = if (isSystemInDarkTheme()) {
                        if (it == selectedOption) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onPrimary
                    } else {
                        if (it == selectedOption) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
                    },
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