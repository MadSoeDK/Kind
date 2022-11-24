package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.*

//TODO set button to fixed size, ignoring the content
@Composable
fun SignUpDonationAmountScreen(
    viewModel: SignUpDonationAmountViewModel,
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingSize.xxxl)

        ) {
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150, viewModel.signUpStepDescription)
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = Shapes.extraSmall
            ) {
                Text(text = "50 kr.", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = Shapes.extraSmall
            ) {
                Text(text = "100 kr.", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = Shapes.extraSmall
            ) {
                Text(text = "200 kr.", color = fieldText)
            }
        }
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            OutlinedButton( //evt. OutlinedTextField?
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = Shapes.extraSmall
            ) {
                Text(text = "Custom amount", color = fieldText)
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
            Button(onClick = { next() }) {
                Text("Next →")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpDonationAmountScreenPreview() {
    val viewModel = SignUpDonationAmountViewModel()
    SignUpDonationAmountScreen(viewModel = viewModel, next = { /*TODO*/ }, back = { /*TODO*/ })
}