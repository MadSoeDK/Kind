package com.example.kind.view.loginAndSignUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.R
import com.example.kind.view.composables.HeaderAndTextWithSelectionFilter
import com.example.kind.view.composables.PortfolioTemplateCard
import com.example.kind.view.theme.Typography

@Composable
fun SignUpDonationSelectionScreen (
    next: () -> Unit,
    back: () -> Unit
){
    Column {
        Row(
        )
        {
            HeaderAndTextWithSelectionFilter(Title = "Who would you like to donate to", Subtitle = "You can adjust the amount to each organization on the next page", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(500.dp),content = {
            items(10 /*TODO: Needs to be adaptive based on the templates*/) {
                PortfolioTemplateCard(
                    Title = "Red Cross",
                    Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                    OrganizationIcon = painterResource(id = R.drawable.bekindsplashart1),
                )
            }
        })

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Column{
                TextButton(onClick = { back() }) {
                    Text(
                        text = "← Back",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineLarge.color
                    )
                }
            }
            Column{
                Button(onClick = { next() }) {
                    Text(
                        text = "Next →",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineSmall.color
                    )
                }
            }
        }
    }
}

