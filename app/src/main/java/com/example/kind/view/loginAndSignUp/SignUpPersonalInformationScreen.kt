package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.R
import com.example.kind.view.loginAndSignUp.SignUpPersonalInformationViewModel
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.LoginHeader
import com.example.kind.view.theme.Typography

@Composable
fun SignUpPersonalInformationScreen(
    viewModel: SignUpPersonalInformationViewModel,
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(0.dp, 15.dp)) {
            LoginHeader(size = 150)
        }
        Text(
            text = viewModel.signUpStepDescription,
            fontWeight = Typography.headlineSmall.fontWeight,
            fontSize = Typography.headlineSmall.fontSize,
            color = Typography.headlineSmall.color,
            textAlign = TextAlign.Center
        )
        Form(
            state = viewModel.formState,
            fields = viewModel.fields,
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 15.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom) {
            Button(onClick = {back()} ) {
                Text("Back")
            }
            Button(onClick = {next()} ) {
                Text("Next")
            }
        }
    }
}