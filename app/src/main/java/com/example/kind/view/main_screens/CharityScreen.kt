package com.example.kind.view.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.NavbarScreens
import com.example.kind.viewModel.CharityViewModel
import com.example.kind.view.theme.Typography
import com.example.kind.view.composables.KindCard
import com.example.kind.view.home.composables.SmallHeaderAndText

@Composable
fun CharityScreen( //TODO:farver skal fikses
    viewModel: CharityViewModel,
) {

    val state by viewModel.data.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(0.dp, 5.dp))
    {
        // Background
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .fillMaxWidth()
            .height(200.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(model = state.mainImage.toString(), contentDescription = null, modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentScale = ContentScale.Crop)
            AsyncImage(model = state.iconImage.toString(), contentDescription = null, modifier = Modifier.size(64.dp).clip(CircleShape).border(1.dp, Color.Black, CircleShape).align(alignment = Alignment.BottomCenter), contentScale = ContentScale.FillBounds)
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            }
        }
        // Donations, Donors & Picture
        Row(modifier = Modifier
            .padding(10.dp, 5.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Text(text = state.donaters.toString()+"\n Donors",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = Typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(50.dp,0.dp))
            Text(text = state.donations.toString()+"\n Donations",
                color = MaterialTheme.colorScheme.secondary,
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
                    text = state.name,
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
        SmallHeaderAndText(headerProvider = "About", textProvider = state.desc)

        // Post
        SmallHeaderAndText(headerProvider = "Posts", textProvider = "Read the latest posts from the organization")


        LazyRow {
            state.articles.forEach {
                item {
                    KindCard(titleProvider = it.title, subTitleProvier = it.charityName, onClick = { viewModel.navController.navigate(NavbarScreens.Article.route + "/" + it.id.toString()) }, /*TODO*/ iconImage = " ", mainImage = " " /*TODO*/)
                }
            }


            /*
            viewModel.getArticles().forEach {
                item {
                    KindCard(titleProvider = "Article " + it.id.toString(), subTitleProvier = it.header, onClick = { viewModel.navController.navigate(NavbarScreens.Article.route + "/" + it.id.toString()) })
                }
            }*/
        }
    }

}