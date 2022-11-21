package com.example.kind.view.login

import android.graphics.Paint.Align
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kind.R
import com.example.kind.ViewModel.PortfolioViewModel
import com.example.kind.view.composables.PortfolioTable

@Composable
fun StartScreen(
    navController: NavController
) {
    Column {
        Text(text = "beKind")
        Image(
            painterResource(
                id = R.drawable.screenshot20220914071147
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
        )
        Text(text = "Start building your portfolio of kindness today!")
        Button(onClick = { navController.navigate("signup") }) {
            Text(text = "Signup")
        }
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginScreen() {
    Text(text = "Login")
}

@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    finishSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                Text(text = "About")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Next")
                }
            }
            1 -> Text(text = "Personal information")
            2 -> {
                Text(text = "Build portfolio")
                Button(onClick = { viewModel.steps.value += 1 }) {
                    Text(text = "Start")
                }
                Button(onClick = { /*TODO skip rest*/ }) {
                    Text(text = "Make it later")
                }
            }
            3 -> Text(text = "Donation frequency")
            4 -> Text(text = "Donation amount")
            5 -> Text(text = "Portfolio builder")
            6 -> {
                DonationSummary()

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { viewModel.steps.value -= 1 }) {
                        Text(text = "Back")
                    }
                    Button(onClick = finishSignup) {
                        Text(text = "Donate")
                    }
                }
            }
        }
        when (viewModel.steps.value) {
            1 -> {
                SignupNavigation(
                    nextStep = { viewModel.steps.value += 1 },
                    prevStep = { viewModel.steps.value -= 1 }
                )
            }
            in 3..5 -> {
                SignupNavigation(
                    nextStep = { viewModel.steps.value += 1 },
                    prevStep = { viewModel.steps.value -= 1 }
                )
            }

        }
    }
}

@Composable
fun SignupNavigation(
    nextStep: () -> Unit,
    prevStep: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = prevStep) {
            Text(text = "Back")
        }
        Button(onClick = nextStep) {
            Text(text = "Next")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationSummary(
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
            text = "Great! You are now set to donate xxx kr. per month to the following charities",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = "Adjust the percentages to each organization below.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )

        PortfolioTable(
            modifier = Modifier,
            columnCount = 4,
            cellWidth = { index ->
                when (index) {
                    0 -> 140.dp
                    1 -> 80.dp
                    2 -> 100.dp
                    else -> 70.dp
                }
            },
            data = PortfolioViewModel().getPortfolioDonation(),
            headerCellContent = { index ->
                val value = when (index) {
                    0 -> "Organization"
                    1 -> "%"
                    2 -> "DKK"
                    else -> ""
                }
                val alignment = when (index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    fontSize = 14.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
            },
            cellContent = { index, item ->

                val value = when (index) {
                    0 -> item.organization
                    1 -> item.pct.toString() + "%"
                    2 -> item.total.toString() + " kr."
                    else -> ""
                }
                val alignment = when (index) {
                    0 -> TextAlign.Left
                    else -> TextAlign.Center
                }
                Text(
                    text = value,
                    fontSize = 12.sp,
                    textAlign = alignment,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        )
        Row {
            Text(
                text = "Pr. month donation. ",
                fontSize = 14.sp
            )
            Text(
                text = "100% ",
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            Text(
                text = PortfolioViewModel().getSpend().toString() + " kr.",
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        val checkedState = remember { mutableStateOf(true) }
        Row {
            Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
            Text(
                text = "Accept terms of service.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 14.dp)
            )
        }
        Row(modifier = Modifier.height(60.dp)) {
            Image(
                painter = painterResource(id = R.drawable.mobilepay_logo),
                contentDescription = ""
            )
        }

        /*
        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = "DAS",
            modifier = Modifier.width(70.dp),
            onValueChange = { },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(
                    0xFF1385623
                ).copy(alpha = 0.2F)
            ),

            label = { Text("") },

        )*/

    }
}