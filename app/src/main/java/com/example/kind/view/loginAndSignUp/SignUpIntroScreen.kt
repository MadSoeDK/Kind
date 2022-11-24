package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.R

@Composable
fun SignUpIntroScreen(
    viewModel: SignUpDonationSummaryViewModel, //change
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.screenshot20220914071147),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = "Build your portfolio of charity now?",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = "You can use the app for one-time donations and make you portfolio later. However you get the most benefits with a portfolio.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )


    }
}