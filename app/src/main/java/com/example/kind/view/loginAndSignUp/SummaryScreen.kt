package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.R
import com.example.kind.viewModel.PortfolioViewModel
import com.example.kind.view.composables.PortfolioTable

@Composable
fun SummaryScreen (
    next: () -> Unit,
    back: () -> Unit
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bekindsplashart1),
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
                text = "PortfolioViewModel().getSpend().toString() +  kr",
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 15.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            TextButton(onClick = { back() }) {
                Text("← Back")
            }
            Button(onClick = { next() }) {
                Text("Next →")
            }
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