package com.example.kind.view.signup_screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes

@Composable
fun DonationAmountScreen(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeader("How much do you want to donate")
        listOf(50, 100, 200).forEach {
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
}