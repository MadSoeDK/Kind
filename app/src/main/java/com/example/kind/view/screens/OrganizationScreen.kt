package com.example.kind.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.CardListHorizontalScroll
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.view.home.composables.SmallHeaderAndText

@Composable
fun OrganizationScreen(
    viewModel: HomeViewModel, // To be Changed
    donorAmount: String,
    donationAmount: String,
    organizationName: String,
    organizationTheme: String
)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(0.dp, 5.dp))

    {

        // Back Button
        Box(
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .clip(CircleShape)
                .background(color = Color.Gray)
                .width(50.dp)
                .height(40.dp)
                .align(Alignment.Start)
        )
        // Background
        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth()
            .height(200.dp)
            .align(Alignment.CenterHorizontally)
        )
        {

            Row(modifier = Modifier
                .align(Alignment.Center)) {
                Box(
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                        .width(150.dp)
                        .height(150.dp)
                )
            }
        }


        // Donations, Donors & Picture
        Row(modifier = Modifier
            .padding(10.dp, 5.dp)
            .align(Alignment.CenterHorizontally)
        )
        {
            Text(text = donorAmount+"\n Donors",
                color = subHeading,
                fontSize = Typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(50.dp,0.dp))

            Text(text = donationAmount+"\n Donations",
                color = subHeading,
                fontSize = Typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )

        }

        // Header & Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.padding(0.dp, 10.dp)
            ){
                Text(
                    text = organizationName,
                    fontWeight = Typography.headlineLarge.fontWeight,
                    fontSize = Typography.headlineLarge.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
            Row {
                Text(text = organizationTheme)
            }
        }

        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Donate Now",
                fontWeight = Typography.labelLarge.fontWeight,
                fontSize = Typography.labelLarge.fontSize,
                color = Typography.headlineLarge.color)
        }

        // About
        SmallHeaderAndText(headerProvider = "About", textProvider = "Lorem Ipsum")

        // Post
        SmallHeaderAndText(headerProvider = "Posts", textProvider = "Read the latest posts from the organization")
        CardListHorizontalScroll(viewModel.getArticles("Article 1", "Article 2"))
    }

}

@Composable
@Preview
fun OrganizationScreenPreview()
{
    val viewModel = viewModel<HomeViewModel>()
    OrganizationScreen(
        viewModel,
        "1.2K",
        "15K",
        "Red Barnet",
        "Disasters")
}