package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.*

//TODO set button to fixed size, ignoring the content
@Composable
fun SignUpDonationFrequencyScreen(
    viewModel: SignUpDonationFrequencyViewModel,
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingSize.xxxxl)
        ) {
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150, viewModel.signUpStepDescription)
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Every month", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Every third month", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Every half year", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Every year", color = fieldText)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 15.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            TextButton(onClick = { back() }) {
                Text("← Back", color = primary)
            }
            Button( //TODO Color
                onClick = { next() },
            ) {
                Text("Next →")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpDonationFrequencyScreenPreview() {
    val viewModel = SignUpDonationFrequencyViewModel()
    SignUpDonationFrequencyScreen(viewModel = viewModel, next = { /*TODO*/ }) {

    }
}