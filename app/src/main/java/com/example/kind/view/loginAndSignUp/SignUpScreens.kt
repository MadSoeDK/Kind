package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.ViewModel.ExplorerViewModel
import com.example.kind.view.screens.PortfolioBuilderScreen
import com.example.kind.view.theme.*

/*@Composable
fun StartScreen(
    navController: NavController
) {
    //er ikke sikker på om vi skal have denne route, siden at start altid er ved login. beholder den for nu in case vi vil have en first time login besked eller lign.
}

@Composable
fun LoginScreen(navController: NavController) {
    LoginView()
}*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    finishSignup: () -> Unit,
    back: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding((paddingSize.standard * 2)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (viewModel.steps.value) {
            0 -> {
                Column(modifier = Modifier
                    .padding(paddingSize.standard),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center,
                ) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Column() {
                            Spacer(modifier = Modifier.size((20).dp))
                            Text("be ",
                                modifier = Modifier,
                                color = primary,
                                fontSize = (fontSize.announcement*2*0.75),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(text = "Kind",
                            color = primary,
                            fontSize = (fontSize.announcement*2),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    //Image
                    Text("Who we are",
                        color = primary,
                        fontSize = fontSize.header,
                        fontWeight = FontWeight.Bold,
                    )
                    Text("Kind is a platform available on web and mobile that makes giving personal, simple and effective by helping users build charitable portfolios they can support with just one monthly donation",
                        color = subHeading,
                        fontSize = fontSize.subheader,)
                    Spacer(modifier = Modifier.size(20.dp))
                    Text("Our Mission",
                        color = primary,
                        fontSize = fontSize.header,
                        fontWeight = FontWeight.Bold,
                    )
                    Text("To reinforce a culture of generosity by creating charitable giving solution that are fun and effective",
                        color = subHeading,
                        fontSize = fontSize.subheader,
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    //Text(text = "About kind page")
                    Button(
                        onClick = { viewModel.steps.value += 1 },
                        modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Start")
                    }
                }
            }
            1 -> SignUpPersonalInformationScreen(
                viewModel = SignUpPersonalInformationViewModel(),
                next = { viewModel.steps.value += 1 },
                back = back
            )
            2 -> {
                Column(
                    modifier = Modifier.padding(paddingSize.xxxxl),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    //Image
                    Text(
                        "Build your portfolio of charity now?\n",
                        textAlign = TextAlign.Center,
                        fontSize = fontSize.signUpHeader,
                    )
                    Text(
                        "You can use the app for one-time donations and make your portfolio later. However you get the most benefits with at portfolio.",
                        textAlign = TextAlign.Center,
                        fontSize = fontSize.subheader,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { viewModel.steps.value += 1 }) {
                        Text(
                            text = "Start",
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = fontSize.buttonText,
                        )
                    }
                    TextButton(onClick = finishSignup) {
                        Text(
                            text = "Make it later",
                            color = fieldText,
                            textAlign = TextAlign.Center,
                            fontSize = fontSize.buttonText
                        )
                    }
                }
            }
            3 -> SignUpDonationFrequencyScreen(
                viewModel = SignUpDonationFrequencyViewModel(),
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            4 -> SignUpDonationAmountScreen(
                viewModel = SignUpDonationAmountViewModel(),
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            5 -> PortfolioBuilderScreen(
                next = { viewModel.steps.value += 1 },
                back = { viewModel.steps.value -= 1 })
            6 -> Text(text = "Portfolio builder")
            7 -> SignUpDonationSummaryScreen(
                viewModel = SignUpDonationSummaryViewModel(),
                next = { finishSignup() },
                back = { viewModel.steps.value -= 1 })
        }
        when (viewModel.steps.value) {
            6 -> {
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
            Text(text = "← Back")
        }
        Button(onClick = nextStep) {
            Text(text = "Next →")
        }
    }
}