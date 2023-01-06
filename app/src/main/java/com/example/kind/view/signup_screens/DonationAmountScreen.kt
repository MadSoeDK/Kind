package com.example.kind.view.signup_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes
import com.example.kind.view.theme.primary

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
        ) {
        LoginHeader(size = 150, "How much would you like to donate")
        listOf(50, 100, 200).forEach {
            OutlinedButton (
                onClick = { onOptionSelected(it) },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .width(300.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (it == selectedOption) primary else primary.copy(alpha = 0.2F),
                    contentColor = if(it == selectedOption) Color.White else Color.Black
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