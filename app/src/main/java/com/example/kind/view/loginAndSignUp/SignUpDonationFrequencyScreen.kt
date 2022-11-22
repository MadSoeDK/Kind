package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Shapes

//TODO set button to fixed size, ignoring the content
@Composable
fun SignUpDonationFrequencyScreen(
    viewModel: SignUpDonationFrequencyViewModel,
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150, viewModel.signUpStepDescription)
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(100.dp, 0.dp),
                shape = Shapes.extraSmall
            ) {
                Text(text = "Every month")
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(100.dp, 0.dp),
                shape = Shapes.extraSmall
            ) {
                Text(text = "Every third month")
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(100.dp, 0.dp),
                shape = Shapes.extraSmall
            ) {
                Text(text = "Every half year")
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(100.dp, 0.dp),
                shape = Shapes.extraSmall
            ) {
                Text(text = "Every year")
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
            Button(onClick = { back() }) {
                Text("Back")
            }
            Button(onClick = { next() }) {
                Text("Next")
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