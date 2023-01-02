package com.example.kind.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.viewModel.CharityViewModel
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.view.composables.KindCard
import com.example.kind.view.home.composables.SmallHeaderAndText

@Composable
fun CharityScreen(
    viewModel: CharityViewModel,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(0.dp, 5.dp))
    {
        // Background
        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth()
            .height(200.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            IconButton(onClick = { viewModel.navController.popBackStack() }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                        .width(75.dp)
                        .height(75.dp)
                )
            }
        }

        // Donations, Donors & Picture
        Row(modifier = Modifier
            .padding(10.dp, 5.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "150\n Donors",
                color = subHeading,
                fontSize = Typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(50.dp,0.dp))
            Text(text = "200\n Donations",
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
                    text = viewModel.data.name,
                    fontWeight = Typography.headlineLarge.fontWeight,
                    fontSize = Typography.headlineLarge.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
            Row {
                Text(text = "Some category here!")
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

        LazyRow {
            viewModel.getArticles().forEach {
                item {
                    KindCard(titleProvider = "Article " + it.id.toString(), subTitleProvier = it.header, onClick = { viewModel.navController.navigate("article" + it.id.toString()) })
                }
            }
        }
    }

}