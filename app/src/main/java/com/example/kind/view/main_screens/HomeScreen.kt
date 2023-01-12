package com.example.kind.view.main_screens

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.NavbarScreens
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.*
import com.example.kind.viewModel.HomeViewModel
import com.example.kind.view.composables.KindCard
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val state by viewModel.data.collectAsState()

    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())

        Column() {
            Row() {
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = "Charity Update",
                        color = MaterialTheme.colorScheme.primary,
                        style = Typography.headlineMedium
                    )
                    Text("The latest news from your charities")
                }
            }
        }
        LazyRow{
            state.articles.forEachIndexed { i,element ->
                item {
                    if (i==0) {Spacer(modifier = Modifier.width(10.dp))}
                    /*if (i==0) {
                        KindCard(
                            modifier = Modifier.padding(),
                            titleProvider = element.header,
                            subTitleProvier = element.header,
                            onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                            }*/
                    KindCard(
                        titleProvider = element.title,
                        subTitleProvier = element.charityName,
                        iconImage = " ", /*TODO*/
                        mainImage = " ", /*TODO*/
                        onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                }
            }
        }
        

        Spacer(modifier = Modifier.height(50.dp))

        Column() {
            Row() {
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = "Explore charities",
                        color = MaterialTheme.colorScheme.primary,
                        style = Typography.headlineLarge
                    )
                    Text("Get to know other charities better")
                }
            }
        }

        LazyRow {
            state.charities.forEachIndexed { i, element ->
                item {
                    if (i==0) {Spacer(modifier = Modifier.width(10.dp))}
                    KindCard(
                        titleProvider = element.name,
                        iconImage = element.iconImage,
                        mainImage = element.mainImage,
                        onClick = {
                            viewModel.navController.navigate(NavbarScreens.Charity.route + "/" + element.id)},
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val viewModel = viewModel<HomeViewModel>()
    HomeScreen(
        viewModel
    )
}