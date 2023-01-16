package com.example.kind.view.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import coil.compose.AsyncImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindCard
import com.example.kind.viewModel.CharityViewModel
import com.example.kind.view.theme.Typography
import com.example.kind.view.home.composables.SmallHeaderAndText

@Composable
fun CharityScreen(
    //TODO:farver skal fikses
    viewModel: CharityViewModel,
) {

    val state by viewModel.data.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(0.dp, 0.dp)
    )
    {
        // Background
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onBackground)
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            AsyncImage(
                model = state.mainImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
            AsyncImage(
                model = state.iconImage,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
                    .align(alignment = Alignment.BottomCenter),
                contentScale = ContentScale.FillBounds
            )
        }
        // Donations, Donors & Picture
        Row(
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = state.name,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                color = Typography.headlineLarge.color,
                textAlign = TextAlign.Center
            )
        }

        //DisplayDonatorsAndDonations(charityViewModel = viewModel)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = state.desc,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize.times(0.6)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            KindButton(onClick = { /*TODO*/ }, textProvider = "Donate", width = 105)
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            OutlinedButton(
                onClick = { viewModel.addToPortfolio() },
                content = { Text(text = "Add to portfolio") },
            )
        }



        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        //DisplayDonatorsAndDonations(charityViewModel = viewModel)



        Column(modifier = Modifier.padding(20.dp, 15.dp)) {
            // Post
            SmallHeaderAndText(
                headerProvider = "Posts",
                textProvider = "Latest news from " + state.name
            )

            LazyRow {
                state.articles.forEach {
                    item {
                        KindCard(
                            titleProvider = it.title,
                            subTitleProvider = it.charityName,
                            onClick = { viewModel.navController.navigate(HomeScreens.Article.route + "/" + it.id) },
                            iconImage = it.iconImage,
                            mainImage = it.mainImage
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayDonatorsAndDonations(charityViewModel: CharityViewModel) {
    val state by charityViewModel.data.collectAsState()
    Row(
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clip(shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(120.dp, 40.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = state.donaters.toString() + " Donators",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize.times(0.6),
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Box(
            Modifier
                .size(120.dp, 40.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = state.donations.toString() + " Donations",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize.times(0.6),
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}