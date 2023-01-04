package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes

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
        ) {
        LoginHeader(size = 150, "How often will you make a donation")

        DonationFrequency.values().forEach {
            OutlinedButton (
                onClick = { onOptionSelected(it) },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .width(300.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (it == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                    contentColor = if(it == selectedOption) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer //onBackground skal måske skiftes
                )
            ) {
                Text(text = it.toString())
            }
        }
        Button(onClick = next) {
            Text(text = "Continue")
        }
    }
}