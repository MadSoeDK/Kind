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
import com.example.kind.ViewModel.CharityViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.CardListHorizontalScroll
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.view.composables.KindCard
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.view.theme.size
import com.example.kind.view.home.composables.SmallHeaderAndText
import com.example.kind.view.theme.paddingSize

@Composable
fun CharityScreen(
    viewModel: CharityViewModel,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White) //TODO WRONG COLOR?
            .padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.xxxs,
            ))

    {

        // Back Button
        Box(
            modifier = Modifier
                .padding(
                    MaterialTheme.paddingSize.s,
                    MaterialTheme.paddingSize.xxxs,
                )
                .clip(CircleShape)
                .background(color = Color.Gray) //TODO
                .width(MaterialTheme.size.xxs)
                .height(MaterialTheme.size.xxxs)
                .align(Alignment.Start)
        )
        // Background
        Box(modifier = Modifier
            .background(color = Color.LightGray) //TODO?
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
                        .padding(
                            MaterialTheme.paddingSize.default,
                            MaterialTheme.paddingSize.s,
                        )
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                        .width(75.dp)
                        .height(75.dp)
                )
            }
        }

        // Donations, Donors & Picture
        Row(modifier = Modifier
            .padding(
                MaterialTheme.paddingSize.s,
                MaterialTheme.paddingSize.xxxs,
            )
            .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "150\n Donors",
                color = subHeading,
                fontSize = Typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(
                MaterialTheme.paddingSize.xxxxl,
                MaterialTheme.paddingSize.default
            ))

            Text(text = donationAmount+"\n Donations",
            Spacer(modifier = Modifier.padding(50.dp,0.dp)) //TODO
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
                .padding(
                    MaterialTheme.paddingSize.default,
                    MaterialTheme.paddingSize.s,
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.padding(
                    MaterialTheme.paddingSize.default,
                    MaterialTheme.paddingSize.s,
                )
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
        SmallHeaderAndText(
            headerProvider = "About",
            textProvider = "Lorem Ipsum"
        )

        // Post
        SmallHeaderAndText(
            headerProvider = "Posts",
            textProvider = "Read the latest posts from the organization"
        )

        LazyRow {
            viewModel.getArticles().forEach {
                item {
                    KindCard(titleProvider = "Article " + it.id.toString(), subTitleProvier = it.header, onClick = { viewModel.navController.navigate("article" + it.id.toString()) })
                }
            }
        }
    }

}