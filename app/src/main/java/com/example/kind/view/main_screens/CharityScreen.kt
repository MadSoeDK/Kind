package com.example.kind.view.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import coil.compose.AsyncImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.kind.HomeScreens
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonEdit
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
        println("State id " + state.id)
        if (state.id.isEmpty()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            // Background
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onBackground)
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                IconButton(
                    onClick = { viewModel.navController.popBackStack() },
                    modifier = Modifier.zIndex(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
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
                        .absoluteOffset(y = 30.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                        .align(alignment = Alignment.BottomCenter),
                    contentScale = ContentScale.FillBounds
                )
            }
            // Donations, Donors & Picture

            Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Text(
                    text = state.name,
                    fontWeight = Typography.headlineLarge.fontWeight,
                    fontSize = Typography.headlineSmall.fontSize,
                    color = Typography.headlineLarge.color,
                    textAlign = TextAlign.Center
                )

            //DisplayDonatorsAndDonations(charityViewModel = viewModel)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))


            Column(
                Modifier.padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = state.desc,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize.times(0.6),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                KindButtonEdit(Onclick = { viewModel.navController.navigate(HomeScreens.Payment.route + "/" + viewModel.id) }, TextProvider = "Donate", width = 120)
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                if (!state.inPortfolio) {
                    OutlinedButton(
                        onClick = { viewModel.addToPortfolio() },
                        content = { Text(text = "Add to portfolio") },
                    )
                }
                else
                {
                    OutlinedButton(
                        onClick = { viewModel.removeFromPortfolio()},
                        content = { Text(text = "Remove from portfolio") },
                    )
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            //DisplayDonatorsAndDonations(charityViewModel = viewModel)


            Column(modifier = Modifier.padding(20.dp, 0.dp)) {
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
}